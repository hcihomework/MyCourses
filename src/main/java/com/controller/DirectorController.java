package com.controller;

import com.bean.CheckCreateBean;
import com.bean.CheckIssueBean;
import com.service.DirectorService;
import com.serviceimpl.DirectorImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("director")
public class DirectorController {
    DirectorService directorService=new DirectorImpl();
    @RequestMapping(value = "findcreate",method = RequestMethod.GET)
    public List<CheckCreateBean> findCreate(){
        return directorService.findCreate();
    }
    @RequestMapping(value = "throughcreate",method = RequestMethod.GET)
    public boolean throughCreate(String course_id){
        return directorService.throughCreate(course_id);
    }
    @RequestMapping(value = "findissue",method = RequestMethod.GET)
    public List<CheckIssueBean> findIssue(){
        return directorService.findIssue();
    }
    @RequestMapping(value = "throughissue",method = RequestMethod.GET)
    public boolean throughIssue(String id){
        return directorService.throughIssue(id);
    }
}
