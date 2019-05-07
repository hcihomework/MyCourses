package com.controller;

import com.entity.Selection;
import com.tool.Mail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("email")
public class SendEmailController {
    Mail mail=new Mail();
    @RequestMapping(value = "send.email",method = RequestMethod.GET)
    public boolean sendEmail(HttpSession session,String student, String talk, String talkDetail){
        talkDetail="教师："+session.getAttribute("user").toString()+"   "+talkDetail;
        return mail.sendMail(student,talk,talkDetail);
    }
    @RequestMapping(value = "send.all.email",method = RequestMethod.GET)
    public boolean sendAllEmail(HttpSession session,List<Selection> students, String talk, String talkDetail){
        if(students!=null){
            for(int i=0;i<students.size();i++){
                talkDetail="教师："+session.getAttribute("user").toString()+"   "+talkDetail;
                mail.sendMail(students.get(i).getStudent(),talk,talkDetail);
            }
        }
        return true;
    }
}
