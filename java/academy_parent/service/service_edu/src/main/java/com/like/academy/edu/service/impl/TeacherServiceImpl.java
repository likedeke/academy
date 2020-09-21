package com.like.academy.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.like.academy.edu.entity.Teacher;
import com.like.academy.edu.entity.vo.TeacherQueryVo;
import com.like.academy.edu.mapper.TeacherMapper;
import com.like.academy.edu.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author like
 * @since 2020-08-20
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    /**
     * 根据讲师条件查询对象查询分页
     *
     * @param pageParam      分页信息：当前多少页，每页多少条信息
     * @param teacherQueryVo 讲师分页查询条件对象
     *
     * @return IPage
     */
    @Override
    public IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {
        //1.默认按照表中的sort字段排序
        QueryWrapper<Teacher> query = new QueryWrapper<>();
        query.orderByAsc("sort");
        if (teacherQueryVo == null) {
            return baseMapper.selectPage(pageParam, query);
        }
        //2.按照传入的teacher分页查询条件对象查询
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();
        if (!StringUtils.isEmpty(name)) {//如果name不为空 like "name%"
            query.likeRight("name", name);
        }
        if (level != null) {//如果lever不为null  lever== xx
            query.eq("level", level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {//如果不为空 join_date<=
            query.ge("join_date", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {//如果不为空 join_date>=
            query.le("join_date", joinDateEnd);
        }
        return baseMapper.selectPage(pageParam, query);
    }

    /**
     * 根据输入的key模糊查询数据库中的姓名
     *
     * @param key 姓名
     *
     * @return name：TeacherName
     */
    @Override
    public List<Map<String, Object>> selectNameList(String key) {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.like("name",key);
        return baseMapper.selectMaps(queryWrapper);
    }

}
