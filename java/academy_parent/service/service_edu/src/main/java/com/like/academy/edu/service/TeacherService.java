package com.like.academy.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.like.academy.edu.entity.Teacher;
import com.like.academy.edu.entity.vo.TeacherQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author like
 * @since 2020-08-20
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 根据讲师条件查询对象查询分页
     * @param pageParam 分页信息：当前多少页，每页多少条信息
     * @param teacherQueryVo  讲师分页查询条件对象
     * @return IPage
     */
    IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo);

    /**
     * 根据输入的key模糊查询数据库中的姓名
     *
     * @param key 姓名
     *
     * @return name：TeacherName
     */
    List<Map<String, Object>> selectNameList(String key);

}
