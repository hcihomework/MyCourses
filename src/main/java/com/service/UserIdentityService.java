package com.service;

import com.bean.DirectorCount;
import com.bean.Person;
import com.bean.TeacherCount;
import com.entity.Users;

public interface UserIdentityService {
    String sendCode(String email);
    String registerUser(Person person);
    String login(String email,String password);
    boolean modifyMessage(String user,String name,String id);
    boolean modifyPassword(String user,String password);
    boolean cancel(String user);
    Users findUserMessage(String user);
    TeacherCount getTeacherCount(String teacher);
    DirectorCount getDirectorCount();
}
