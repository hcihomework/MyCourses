package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="news")
public class News {
    @Id
    private String id;
    private String fromWhere;
    private String toWhere;
    private String type;
    private String time;
    private String message;
    public News(){}
    public News(String id,String fromWhere,String toWhere,String type,String time,String message){
        this.id=id;
        this.fromWhere=fromWhere;
        this.toWhere=toWhere;
        this.type=type;
        this.time=time;
        this.message=message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public String getToWhere() {
        return toWhere;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setToWhere(String toWhere) {
        this.toWhere = toWhere;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
