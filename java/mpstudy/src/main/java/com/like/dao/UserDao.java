package com.like.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.like.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author likeLove
 * @time 2020-08-20  9:10
 */
@Repository
public interface UserDao extends BaseMapper<User> {}
