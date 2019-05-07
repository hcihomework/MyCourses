package com.bean;

public class CheckCreateBean {
    private String id;
    private String name;
    private String note;
    private String grade;
    private String time;
    private String approval;
    private String teacher;
    public CheckCreateBean(){}
    public CheckCreateBean(String id,String name,String note,String grade,String time,String approval,String teacher){
        this.id=id;
        this.name=name;
        this.note=note;
        this.grade=grade;
        this.time=time;
        this.approval=approval;
        this.teacher=teacher;
    }

    public String getId() {
        return id;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public String getApproval() {
        return approval;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher() {
        return teacher;
    }
}
