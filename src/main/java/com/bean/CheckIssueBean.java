package com.bean;

public class CheckIssueBean {
    private String id;
    private String class_id;
    private String name;
    private String grade;
    private String begin_time;
    private String time;
    private String limit_class;
    private String limit_student;
    private String choice_student;
    private String approval;
    public CheckIssueBean(){}
    public CheckIssueBean(String id,String class_id,String name,String grade,String begin_time,String time,String limit_class,String limit_student,
                          String choice_student,String approval){
        this.id=id;
        this.class_id=class_id;
        this.name=name;
        this.grade=grade;
        this.begin_time=begin_time;
        this.time=time;
        this.limit_class=limit_class;
        this.limit_student=limit_student;
        this.choice_student=choice_student;
        this.approval=approval;
    }

    public String getGrade() {
        return grade;
    }

    public String getApproval() {
        return approval;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getId() {
        return id;
    }

    public void setLimit_student(String limit_student) {
        this.limit_student = limit_student;
    }

    public void setLimit_class(String limit_class) {
        this.limit_class = limit_class;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public String getChoice_student() {
        return choice_student;
    }

    public String getLimit_class() {
        return limit_class;
    }

    public String getLimit_student() {
        return limit_student;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public void setChoice_student(String choice_student) {
        this.choice_student = choice_student;
    }
}
