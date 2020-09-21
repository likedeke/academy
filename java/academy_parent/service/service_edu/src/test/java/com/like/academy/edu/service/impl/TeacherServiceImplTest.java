package com.like.academy.edu.service.impl;

import com.like.academy.edu.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith (SpringRunner.class)
public class TeacherServiceImplTest {
    TeacherService teacherService;
    @Test
    public void test() {
        List<Map<String, Object>> nameList = teacherService.selectNameList("周");
        nameList.forEach(System.out::println);
    }
}