package com.controller;

import com.bean.DirectorCount;
import com.bean.Person;
import com.bean.TeacherCount;
import com.entity.Users;
import com.service.UserIdentityService;
import com.serviceimpl.UserIdentityImpl;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@RestController
public class UserIdentityController {
    UserIdentityService userIdentityService=new UserIdentityImpl();
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(HttpSession session,@RequestBody Person person){
        return userIdentityService.registerUser(person);

        /*--------------------------验证码验证----------------------》
        if(session.getAttribute("code")!=null&&session.getAttribute("code").toString().equals(person.getCapthcha()))
            return userIdentityService.registerUser(person);
        else {
            System.out.println(person.getCapthcha()+" "+person.getEmail());
            System.out.println(session.getAttribute("code"));
            return "验证码错误";
        }
       */
    }
    @RequestMapping(value = "sendcode",method = RequestMethod.GET)
    public boolean sendCode(HttpSession session,String email){
        String code=userIdentityService.sendCode(email);
        if(code!=null) {
            session.setAttribute("code",code);
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpSession session,String email,String password){
        //System.out.println(userIdentityService.login(email,password)?"success":"fail");
        String res=userIdentityService.login(email,password);
        if(res!="fail"){
            session.setAttribute("user",email);
        }
        return res;
    }
    @RequestMapping(value = "get.user",method = RequestMethod.GET)
    public String getUser(HttpSession session){
       String user="";
       if(session.getAttribute("user")!=null){
           user=session.getAttribute("user").toString();
       }
       return user;
    }
    @RequestMapping(value = "modify.message",method = RequestMethod.GET)
    public boolean modifyMessage(HttpSession session,String name,String id){
        try{
            String user=session.getAttribute("user").toString();
            userIdentityService.modifyMessage(user,name,id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @RequestMapping(value = "modify.password",method = RequestMethod.GET)
    public boolean modifyPassword(HttpSession session,String password){
        try{
            String user=session.getAttribute("user").toString();
            userIdentityService.modifyPassword(user,password);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @RequestMapping(value = "cancel",method = RequestMethod.GET)
    public boolean cancel(HttpSession session){
        try{
            String user=session.getAttribute("user").toString();
            userIdentityService.cancel(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @RequestMapping(value = "find.user.message",method = RequestMethod.GET)
    public Users findUserMessage(HttpSession session){
        try{
            String user=session.getAttribute("user").toString();
            return userIdentityService.findUserMessage(user);
        }catch (Exception e){
            e.printStackTrace();
            return new Users();
        }
    }
    @RequestMapping(value = "find.user.count.message",method = RequestMethod.GET)
    public TeacherCount getTeacherCount(HttpSession session){
        return userIdentityService.getTeacherCount(session.getAttribute("user").toString());
    }
    @RequestMapping(value = "find.director.count.message",method = RequestMethod.GET)
    public DirectorCount getDirectorCount(){
        return userIdentityService.getDirectorCount();
    }
}
