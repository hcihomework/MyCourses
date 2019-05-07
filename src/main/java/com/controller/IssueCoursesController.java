package com.controller;

import com.service.IssueCoursesService;
import com.serviceimpl.IssueCoursesImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("issue")
public class IssueCoursesController {
    @RequestMapping(value = "issuecourses")
    public boolean issueCourses(String classId,String time,String limitClass,String limitStudent){
        //System.out.println(time);
        IssueCoursesService issueCoursesService=new IssueCoursesImpl();
        return issueCoursesService.issueCourses(classId,time,limitClass,limitStudent);
    }
}
