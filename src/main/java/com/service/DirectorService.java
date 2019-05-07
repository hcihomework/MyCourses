package com.service;

import com.bean.CheckCreateBean;
import com.bean.CheckIssueBean;

import java.util.List;

public interface DirectorService {
    List<CheckCreateBean> findCreate();
    boolean throughCreate(String course_id);
    List<CheckIssueBean> findIssue();
    boolean throughIssue(String id);
}
