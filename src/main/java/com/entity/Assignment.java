package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    private String id;
    private String name;
    private String courses;
    private String deadline;
    private String message;
    private String time;
    public Assignment(){}
    public Assignment(String id,String name,String courses,String deadline,String message,String time){
        this.id=id;
        this.name=name;
        this.courses=courses;
        this.deadline=deadline;
        this.message=message;
        this.time=time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getDeadline() {
        return deadline;
    }


    public String getMessage() {
        return message;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
