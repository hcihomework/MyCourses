package com.controller;

import com.bean.CheckCreateBean;
import com.bean.CheckIssueBean;
import com.bean.PreviewCourses;
import com.service.SearchCoursesService;
import com.serviceimpl.SearchCoursesImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchCoursesController {
    SearchCoursesService searchCoursesService=new SearchCoursesImpl();
    @RequestMapping(value = "coursesissue")
    public List<PreviewCourses> findNeedIssue(HttpSession session){

        List<PreviewCourses> previewCoursesList=searchCoursesService.findNeedIssue(session.getAttribute("user").toString());
        return previewCoursesList;
    }
    @RequestMapping(value = "checkcreatelist")
    public List<CheckCreateBean> findCheckCreateList(HttpSession session){

        return searchCoursesService.findCheckCreateList(session.getAttribute("user").toString());
    }
    @RequestMapping(value = "checkissuelist")
    public List<CheckIssueBean> findCheckIssueList(HttpSession session){
        return searchCoursesService.findCheckIssueList(session.getAttribute("user").toString());
    }
}
