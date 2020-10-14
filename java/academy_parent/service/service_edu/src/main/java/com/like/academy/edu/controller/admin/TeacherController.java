package com.like.academy.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.like.academy.edu.entity.Teacher;
import com.like.academy.edu.entity.vo.TeacherQueryVo;
import com.like.academy.edu.feign.OssFileService;
import com.like.academy.edu.service.TeacherService;
import com.lk.academy.common.base.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author like
 * @since 2020 -08-20
 */
@CrossOrigin
@RestController
@RequestMapping ("/admin/edu/teacher")
@Api (tags = "讲师管理")
public class TeacherController {
    /**
     * The Teacher service.
     */
    @Autowired
    TeacherService teacherService;
    /**
     * The Oss file service.
     */
    @Autowired
    OssFileService ossFileService;

    /**
     * 根据id获取讲师
     *
     * @param id 讲师id
     *
     * @return Result teacher by id
     */
    @GetMapping ("/{id}")
    @ApiOperation (value = "根据id获取讲师")
    public Result getTeacherById(@ApiParam ("讲师id") @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return Result.ok().data("item", teacher);
        }
        return Result.error().message("讲师不存在");
    }

    /**
     * 获取所有讲师列表
     *
     * @return Result all teacher for list
     */
    @GetMapping ("")
    @ApiOperation (value = "获取所有讲师列表")
    public Result getAllTeacherForList() {
        List<Teacher> list = teacherService.list();
        return Result.ok().data("items", list);
    }

    /**
     * 讲师分页列表
     *
     * @param page           当前页码数
     * @param limit          每页显示的条目数
     * @param teacherQueryVo 讲师条件查询对象
     *
     * @return Result result
     */
    @GetMapping ("/{page}/{limit}")
    @ApiOperation ("讲师分页列表")
    public Result pageList(@ApiParam (value = "当前页码", required = true) @PathVariable long page,
                           @ApiParam (value = "每页有多少条记录", required = true) @PathVariable long limit,
                           @ApiParam ("讲师查询条件对象") TeacherQueryVo teacherQueryVo) {
        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords(); // 获取分页的数据
        long total = pageModel.getTotal(); // 总记录条数
        return Result.ok().data("total", total).data("rows", records);
    }

    /**
     * 根据关键字查询讲师名列表
     *
     * @param key 关键字
     *
     * @return Result result
     */
    @ApiOperation ("根据关键字查询讲师名列表")
    @GetMapping ("/list/{key}")
    public Result selectNameListByKey(@ApiParam (value = "关键字", required = true) @PathVariable String key) {
        List<Map<String, Object>> nameList = teacherService.selectNameList(key);
        return Result.ok().data("nameList", nameList);
    }

    /**
     * 添加讲师
     *
     * @param teacher 需要添加的讲师
     *
     * @return Result result
     */
    @PostMapping ("")
    @ApiOperation (value = "添加讲师")
    public Result save(@ApiParam ("需要添加的讲师") @RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return Result.ok().message("保存成功");
        }
        return Result.error().message("保存失败");
    }

    /**
     * 修改讲师信息
     *
     * @param teacher 需要修改的讲师
     *
     * @return Result result
     */
    @PutMapping ("")
    @ApiOperation (value = "修改讲师信息")
    public Result updateById(@ApiParam ("需要修改的讲师") @RequestBody Teacher teacher) {
        boolean save = teacherService.updateById(teacher);
        if (save) {
            return Result.ok().message("修改成功");
        }
        return Result.error().message("修改失败");
    }

    /**
     * 根据讲师id删除讲师
     *
     * @param id 讲师id
     *
     * @return Result result
     */
    @DeleteMapping ("/{id}")
    @ApiOperation (value = "根据讲师id删除讲师", notes = "根据讲师id删除讲师,逻辑删除")
    public Result removeById(@ApiParam (value = "讲师id") @PathVariable String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            ossFileService.del();
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("删除失败");
        }
    }

    /**
     * Test open feign result.
     *
     * @return the result
     */
    @GetMapping ("/test")
    @ApiOperation (value = "测试oss删除方法")
    public Result testOpenFeign() {
        ossFileService.del();
        return Result.ok();
    }

    /**
     * 根据讲师id列表删除讲师
     *
     * @param idList 讲师id列表
     *
     * @return Result result
     */
    @DeleteMapping ("/batch-remove")
    @ApiOperation (value = "根据讲师id列表删除讲师", notes = "根据讲师id列表删除讲师,逻辑删除")
    public Result removeByIdList(@ApiParam (value = "讲师id列表", required = true) @RequestBody List<String> idList) {
        boolean b = teacherService.removeByIds(idList);
        if (b) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("删除失败");
        }
    }
}

