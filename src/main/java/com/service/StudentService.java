package com.service;

import com.bean.CheckIssueBean;
import com.bean.StudentCount;
import com.entity.Selection;

import java.util.List;

public interface StudentService {
    List<CheckIssueBean> findIssueCourse();
    String chooseCourse(String issue_id,String student);
    List<CheckIssueBean> findMyCourses(String student);
    List<Selection> findCourseStudent(String issue_id);
    StudentCount getStudentCount(String student);
}
