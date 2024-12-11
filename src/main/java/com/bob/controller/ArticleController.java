package com.bob.controller;

import com.bob.pojo.Article;
import com.bob.pojo.PageBean;
import com.bob.pojo.Result;
import com.bob.service.ArticelService;
import com.bob.utils.JwtUtil;
import com.bob.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticelService articelService;

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
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article) {

        articelService.add(article);
        return Result.success("添加成功");
    }
    @GetMapping
    public Result<PageBean<Article>> List(Integer pageNum, Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,
                                          @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articelService.List(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }
}
