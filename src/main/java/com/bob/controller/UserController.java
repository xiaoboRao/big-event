package com.bob.controller;

import com.bob.pojo.Result;
import com.bob.pojo.User;
import com.bob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserService userService;

    @RequestMapping("/register")
    public Result register(String username, String password) {
        // 查询用户
        User u = userService.findUserByUserName(username);

        if(u == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名被占用");
        }
    }
}
