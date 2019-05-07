package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="issue_courses")
public class IssueCourses {
    @Id
    private String id;
    private String class_id;
    private String time;
    private int limit_class;
    private int limit_student;
    private String issue_time;
    private boolean approval;
    public IssueCourses(){}
    public IssueCourses(String id,String class_id,String time,int limit_class,int limit_student,String issue_time,boolean approval){
        this.id=id;
        this.class_id=class_id;
        this.time=time;
        this.limit_class=limit_class;
        this.limit_student=limit_student;
        this.issue_time=issue_time;
        this.approval=approval;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public boolean isApproval() {
        return approval;
    }

    public int getLimit_class() {
        return limit_class;
    }

    public int getLimit_student() {
        return limit_student;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getIssue_time() {
        return issue_time;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public void setIssue_time(String issue_time) {
        this.issue_time = issue_time;
    }

    public void setLimit_class(int limit_class) {
        this.limit_class = limit_class;
    }

    public void setLimit_student(int limit_student) {
        this.limit_student = limit_student;
    }
}
