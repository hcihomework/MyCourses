package com.serviceimpl;

import com.entity.News;
import com.repository.NewsRepository;
import com.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
@Component
public class NewsImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    private static NewsImpl newsImpl;
    @PostConstruct
    public void init(){
        newsImpl=this;
        newsImpl.newsRepository=this.newsRepository;
    }
    @Override
    public List<News> findNewsList(String user) {
        return newsImpl.newsRepository.findByToWhere(user);
    }
}
