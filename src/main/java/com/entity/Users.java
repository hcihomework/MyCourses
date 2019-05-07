package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="users")
public class Users implements Serializable {
    @Id
    @Column(name = "email",length = 40)
    private String email;
    @Column(name = "name",length = 40)
    private String name;
    @Column(name = "identity",length = 40)
    private String identity;
    @Column(name = "number",length = 40)
    private String number;
    @Column(name = "time",length = 30)
    private String time;
    @Column(name = "is_using")
    private boolean isUsing;
    public Users(){}
    public Users(String email,String name,String identity,String number,String time,boolean isUsing){
        this.email=email;
        this.name=name;
        this.identity=identity;
        this.number=number;
        this.time=time;
        this.isUsing=isUsing;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public String getIdentity() {
        return identity;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsing(boolean using) {
        isUsing = using;
    }

    public boolean isUsing() {
        return isUsing;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
