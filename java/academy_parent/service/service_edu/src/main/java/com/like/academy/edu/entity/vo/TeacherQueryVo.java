package com.like.academy.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author likeLove
 * @time 2020-08-21  11:43
 */
@Data
@ApiModel (value = "Teacher条件查询对象", description = "讲师条件查询对象")
public class TeacherQueryVo implements Serializable {

    private static final long serialVersionUID = 3647151358861270122L;

    @ApiModelProperty (value = "讲师姓名", example = "李老师")
    private String name;

    @ApiModelProperty (value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty (value = "加入时间范围的起始时间")
    private String joinDateBegin;

    @ApiModelProperty (value = "加入时间范围的结尾时间")
    private String joinDateEnd;
}
