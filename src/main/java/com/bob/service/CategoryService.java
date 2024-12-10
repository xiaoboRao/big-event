package com.bob.service;

import com.bob.pojo.Category;

import java.util.List;

public interface CategoryService {

    public void add(Category category);

    List<Category> list(Integer userId);

    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);
}
