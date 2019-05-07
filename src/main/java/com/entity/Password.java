package com.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="password")
public class Password {
    @Id
    @Column(name = "email",length = 40)
    private String email;
    @Column(name = "password",length = 200)
    private String password;
    public Password(){}
    public Password(String email,String password){
        this.email=email;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
