package com.bob.controller;

import com.bob.pojo.Result;
import com.bob.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @RequestMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/) {

        /*try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
        } catch (Exception e) {
            response.setStatus(401);
            return Result.error("未登录");
        }*/

        return Result.success("获取文章成功");

    }
}
