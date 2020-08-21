package com.like.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author likeLove
 * @time 2020-08-20  11:24
 */
@Data
public class Product {
   private long id;
   private String name;
   private Integer price;
   @Version
   private Integer version;
}
