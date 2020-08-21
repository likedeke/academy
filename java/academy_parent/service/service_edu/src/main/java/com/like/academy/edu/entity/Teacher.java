package com.like.academy.edu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.academy.server.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author like
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode (callSuper = true)
@Accessors (chain = true)
@TableName ("edu_teacher")
@ApiModel (value = "Teacher对象", description = "讲师")
public class Teacher extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "讲师姓名", example = "李老师")
    private String name;

    @ApiModelProperty (value = "讲师简介")
    private String intro;

    @ApiModelProperty (value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty (value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty (value = "讲师头像")
    private String avatar;

    @ApiModelProperty (value = "排序")
    private Integer sort;

    @ApiModelProperty (value = "入驻时间", example = "2020-08-21")
    @JsonFormat (timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date joinDate;

    @ApiModelProperty (value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField ("is_deleted")
    @TableLogic
    private Boolean deleted;


}