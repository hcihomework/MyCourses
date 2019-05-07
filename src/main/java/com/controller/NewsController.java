package com.controller;

import com.entity.News;
import com.service.NewsService;
import com.serviceimpl.NewsImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsController {
    NewsService newsService=new NewsImpl();
    @RequestMapping(value = "select.news",method = RequestMethod.GET)
    public List<News> findNewsList(HttpSession session){
        try{
            String user=session.getAttribute("user").toString();
            return newsService.findNewsList(user);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
