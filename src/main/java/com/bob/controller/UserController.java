package com.bob.controller;

import com.auth0.jwt.JWT;
import com.bob.pojo.Result;
import com.bob.pojo.User;
import com.bob.service.UserService;
import com.bob.utils.JwtUtil;
import com.bob.utils.Md5Util;
import com.bob.utils.ThreadLocalUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户
        User u = userService.findUserByUserName(username);

        if (u == null) {
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

        if (u == null) {
            return Result.error("用户名错误");
        } else {
            if (Md5Util.getMD5String(password).equals(u.getPassword())) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", u.getUsername());
                claims.put("id", u.getId());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        }
    }

    @RequestMapping("/userInfo")
    public Result<User> getUserInfo(@RequestHeader(name = "Authorization") String token) {
        try {
//          Map<String, Object> clainms = JwtUtil.parseToken(token);
            Map<String, Object> claims = ThreadLocalUtil.get();
            String username = (String) claims.get("username");
            User user = userService.findUserByUserName(username);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("获取失败");
        }

    }

    @RequestMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody @Validated User user) {
        user.setUpdateTime(LocalDateTime.now());
        userService.updateUserInfo(user);
        return Result.success("更新用户信息成功");
    }

    @RequestMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, Object> params) {

        String oldPwd = (String) params.get("old_pwd");
        String newPwd = (String) params.get("new_pwd");
        String rePwd = (String) params.get("re_pwd");
        if(StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findUserByUserName(username);
        if(!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("密码填写错误");
        }

        if(!newPwd.equals(rePwd)) {
            return Result.error("两次填写的密码不一样");
        }
        
        userService.updatePwd(newPwd);
        
        return Result.success("修改密码成功");

    }
}
