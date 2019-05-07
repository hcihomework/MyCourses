package com.entity;

import javax.persistence.*;

@Entity
@Table(name="courses")
public class Courses {
    @Id
    private String id;
    private String time;
    private String teacher;
    private String name;
    private String grade;
    private String message;
    private boolean approval;
    public Courses(){}
    public Courses(String id,String time,String teacher,String name,String grade,String message,boolean approval){
        this.id=id;
        this.time=time;
        this.teacher=teacher;
        this.name=name;
        this.grade=grade;
        this.message=message;
        this.approval=approval;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }
}
