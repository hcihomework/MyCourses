package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_assignment")
public class StudentAssignment {
    @Id
    private String id;
    private String assignment;
    private String student;
    private String path;
    private String time;
    private String score;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getAssignment() {
        return assignment;
    }

    public String getScore() {
        return score;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
