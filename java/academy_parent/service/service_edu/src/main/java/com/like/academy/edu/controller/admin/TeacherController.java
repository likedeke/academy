package com.like.academy.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.like.academy.edu.entity.Teacher;
import com.like.academy.edu.entity.vo.TeacherQueryVo;
import com.like.academy.edu.service.TeacherService;
import com.lk.academy.common.base.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author like
 * @since 2020-08-20
 */
@RestController
@RequestMapping ("/admin/edu/teacher")
@Api (tags = "讲师管理")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping ("/{id}")
    @ApiOperation (value = "根据id获取讲师")
    public Result getTeacherById(@ApiParam("讲师id") @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher!=null) {
            return Result.ok().data("item",teacher);
        }
        return Result.error().message("讲师不存在");
    }

    @GetMapping ("")
    @ApiOperation (value = "获取所有讲师列表")
    public Result getAllTeacherForList() {
        List<Teacher> list = teacherService.list();
        return Result.ok().data("items", list);
    }

    @GetMapping ("/{page}/{limit}")
    @ApiOperation ("讲师分页列表")
    public Result pageList(@ApiParam (value = "当前页码", required = true) @PathVariable long page, @ApiParam (value = "每页有多少条记录", required = true) @PathVariable long limit, @ApiParam ("讲师查询条件对象") TeacherQueryVo teacherQueryVo) {
        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();//获取分页的数据
        long total = pageModel.getTotal();//总记录条数
        return Result.ok().data("total", total).data("rows", records);
    }

    @PostMapping ("")
    @ApiOperation (value = "添加讲师")
    public Result save( @ApiParam("需要添加的讲师") @RequestBody  Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return Result.ok().message("保存成功");
        }
        return Result.error().message("保存失败");
    }

    @PutMapping ("")
    @ApiOperation (value = "修改讲师信息")
    public Result updateById(@ApiParam ("需要修改的讲师") @RequestBody Teacher teacher) {
        boolean save = teacherService.updateById(teacher);
        if (save) {
            return Result.ok().message("修改成功");
        }
        return Result.error().message("修改失败");
    }

    @DeleteMapping ("/{id}")
    @ApiOperation (value = "根据讲师id删除讲师", notes = "根据讲师id删除讲师,逻辑删除")
    public Result removeById(@ApiParam (value = "讲师id") @PathVariable String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return Result.ok().message("删除成功");
        }
        else {
            return Result.error().message("删除失败");
        }
    }

}

