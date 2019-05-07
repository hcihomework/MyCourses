package com.service;

import com.entity.News;

import java.util.List;

public interface NewsService {
    List<News> findNewsList(String user);
}
