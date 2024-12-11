package com.bob.service;

import com.bob.pojo.Article;
import com.bob.pojo.PageBean;

public interface ArticelService {

    void add(Article article);

    PageBean<Article> List(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
