package com.like.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @author likeLove
 * @time 2020-08-20  9:07
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //插入时填充字段
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //插入和更新时填充字段
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //逻辑删除
    @TableLogic
    private Integer deleted;
}
