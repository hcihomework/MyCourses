package com.bean;

public class Person {
    private String email;
    private String name;
    private String identity;
    private String id;
    private String password;
    private String captcha;
    public Person(){}
    public Person(String email,String name,String identity,String id,String password,String captcha){
        this.email=email;
        this.name=name;
        this.identity=identity;
        this.id=id;
        this.password=password;
        this.captcha=captcha;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCapthcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
