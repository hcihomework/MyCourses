package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courseware")
public class Courseware {
    @Id
    private String path;
    private String courses_id;
    public Courseware(){}
    public Courseware(String path,String courses_id){
        this.path=path;
        this.courses_id=courses_id;
    }

    public String getCourses_id() {
        return courses_id;
    }

    public String getPath() {
        return path;
    }

    public void setCourses_id(String courses_id) {
        this.courses_id = courses_id;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
