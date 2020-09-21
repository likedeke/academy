package com.like.academy.edu.controller;

import com.lk.academy.common.base.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author likeLove
 * @time 2020-08-23  15:42
 */
@CrossOrigin    //允许跨域请求
@RestController
@RequestMapping ("/user")
public class LoginController {
    @ApiOperation ("用户登录功能")
    @PostMapping ("/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @GetMapping ("/info")
    @ApiOperation ("验证用户信息")
    public Result info() {
        return Result.ok()
                .data("name", "admin")
                .data("roles", "[admin]")
                .data("avatar","https://apic.douyucdn" +
                        ".cn/upload/avatar_v3/201912/20a8105803b5452a9bb1e971b3dfce1a_middle.jpg");
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }
}
