package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "assignment_ware")
public class AssignmentWare {
    @Id
    private String id;
    private String assignment_id;
    private String path;
    public AssignmentWare(){}
    public AssignmentWare(String id,String assignment_id,String path){
        this.id=id;
        this.assignment_id=assignment_id;
        this.path=path;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getAssignment_id() {
        return assignment_id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }
}
