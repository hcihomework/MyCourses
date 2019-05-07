package com.controller;

import com.bean.CheckIssueBean;
import com.bean.StudentCount;
import com.entity.Selection;
import com.service.StudentService;
import com.serviceimpl.StudentImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    StudentService studentService=new StudentImpl();
    @RequestMapping(value = "issue.course.list",method = RequestMethod.GET)
    public List<CheckIssueBean> findIssueCourse(){
        return studentService.findIssueCourse();
    }
    @RequestMapping(value = "choose.course",method = RequestMethod.GET)
    public String chooseCourse(HttpSession session,String issue_id){
        return studentService.chooseCourse(issue_id,session.getAttribute("user").toString());
    }
    @RequestMapping(value = "my.courses",method = RequestMethod.GET)
    public List<CheckIssueBean> findMyCourses(HttpSession session){
        return studentService.findMyCourses(session.getAttribute("user").toString());
    }
    @RequestMapping(value = "courses.students",method = RequestMethod.GET)
    public List<Selection> findCourseStudent(String issue_id){
        return studentService.findCourseStudent(issue_id);
    }
    @RequestMapping(value = "count.students",method = RequestMethod.GET)
    public StudentCount getStudentCount(HttpSession session){
        return studentService.getStudentCount(session.getAttribute("user").toString());
    }

}
