package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="forum")
public class Forum {
    @Id
    private String id;
    private String course_id;
    private String from_id;
    private String to_id;
    private String message;
    private String time;
    private String talk;
    public Forum(){}
    public Forum(String id,String course_id,String from_id,String to_id,String message,String time,String talk){
        this.id=id;
        this.course_id=course_id;
        this.from_id=from_id;
        this.to_id=to_id;
        this.message=message;
        this.time=time;
        this.talk=talk;
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

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getFrom_id() {
        return from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }
}
