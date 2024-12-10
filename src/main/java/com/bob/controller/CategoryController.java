package com.bob.controller;

import com.bob.pojo.Category;
import com.bob.pojo.Result;
import com.bob.service.CategoryService;
import com.bob.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated(Category.add.class) Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> user = ThreadLocalUtil.get();
        Integer userId = (Integer) user.get("id");
        category.setCreateUser(userId);
        System.out.println(category);
        categoryService.add(category);
        return Result.success("添加成功");
    }

    @GetMapping
    public List<Category> list() {
        Map<String,Object> user = ThreadLocalUtil.get();
        Integer userId = (Integer) user.get("id");
        return categoryService.list(userId);
    }
    @GetMapping("/detail")
    public Category findById(Integer id) {
        return categoryService.findById(id);
    }

    @PutMapping
    public Result update(@RequestBody  @Validated(Category.add.class) Category category) {
         categoryService.update(category);
         return Result.success("更新成功");
    }

    @DeleteMapping
    public Result delete(Integer id) {
        categoryService.delete(id);
        return Result.success("删除成功");
    }


}
