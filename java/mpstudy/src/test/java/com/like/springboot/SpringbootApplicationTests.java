package com.like.springboot;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.like.dao.ProductDao;
import com.like.dao.UserDao;
import com.like.entity.Product;
import com.like.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith (SpringRunner.class)
@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @Test
    void contextLoads() {
        List<User> users = userDao.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void insert() {
        User user = new User();
        user.setAge(18);
        user.setEmail("980650920@qq.com");
        user.setName("like");
        int insert = userDao.insert(user);
        System.out.println(insert);
    }

    @Test
    void del() {
        int i = userDao.deleteById(1);
        System.out.println(i);
    }

    @Test
    void update() {
        User user = userDao.selectById(1296282758598995969l);
        user.setAge(20);
        user.setName("here is like");
        userDao.updateById(user);
    }

    @Test
    void pruduct() {
        Product p1 = productDao.selectById(1);
        //小李修改
        System.out.println("==========小李============");
        System.out.println(p1.getPrice());
        p1.setPrice(p1.getPrice()+50);
        int i = productDao.updateById(p1);
        //小王
        System.out.println("==========小王============");
        p1.setPrice(p1.getPrice()-30);
        int i2 = productDao.updateById(p1);
        if (i2 <1) {
            System.out.println("更新失败");
        }
    }

    @Test
    void selectForPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name");

        Page<Map<String,Object>> userPage = new Page<>(1, 5);
        Page<Map<String, Object>> pageParam = userDao.selectMapsPage(userPage, queryWrapper);

        List<Map<String, Object>> records = pageParam.getRecords();
        records.forEach(System.out::println);

        System.out.println(pageParam.getPages());
        System.out.println(pageParam.getTotal());
        System.out.println(pageParam.getCurrent());
        System.out.println(pageParam.getSize());
        System.out.println(pageParam.hasNext());
        System.out.println(pageParam.hasPrevious());
    }
}
