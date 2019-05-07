package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="selection")
public class Selection {
    @Id
    private String id;
    private String issue_course;
    private String student;
    private String time;
    private boolean drop_course;
    public Selection(){}
    public Selection(String id,String issue_course,String student,String time,boolean drop_course){
        this.id=id;
        this.issue_course=issue_course;
        this.student=student;
        this.time=time;
        this.drop_course=drop_course;
    }

    public String getId() {
        return id;
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

    public void setStudent(String student) {
        this.student = student;
    }

    public String getStudent() {
        return student;
    }

    public String getIssue_course() {
        return issue_course;
    }

    public void setIssue_course(String issue_course) {
        this.issue_course = issue_course;
    }

    public boolean isDrop_course() {
        return drop_course;
    }

    public void setDrop_course(boolean drop_course) {
        this.drop_course = drop_course;
    }
}
