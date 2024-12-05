package com.bob.controller;

import com.bob.pojo.Result;
import com.bob.pojo.User;
import com.bob.service.UserService;
import com.bob.utils.JwtUtil;
import com.bob.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private  UserService userService;

    @RequestMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户
        User u = userService.findUserByUserName(username);

        if(u == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名被占用");
        }
    }

    @RequestMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户
        User u = userService.findUserByUserName(username);

        if(u == null) {
            return Result.error("用户名错误");
        } else {
            if(Md5Util.getMD5String(password).equals(u.getPassword())) {
                Map<String,Object> claims = new HashMap<>();
                claims.put("username",u.getUsername());
                claims.put("id",u.getId());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        }
    }
}
