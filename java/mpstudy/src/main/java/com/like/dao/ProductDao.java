package com.like.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.like.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * @author likeLove
 * @time 2020-08-20  11:32
 */
@Repository
public interface ProductDao extends BaseMapper<Product> {}
