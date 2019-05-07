package com.service;

import com.bean.CheckCreateBean;
import com.bean.CheckIssueBean;
import com.bean.PreviewCourses;

import java.util.List;

public interface SearchCoursesService {
    List<PreviewCourses> findNeedIssue(String teacher);
    List<CheckCreateBean> findCheckCreateList(String teacher);
    List<CheckIssueBean> findCheckIssueList(String teacher);
}
