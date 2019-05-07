package com.controller;

import com.bean.Talk;
import com.service.TopicService;
import com.serviceimpl.TopicImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("topic")
public class TopicController {
    TopicService topicService=new TopicImpl();
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public boolean addTopic(HttpSession session,String courseId, String talk, String talkDetail){
        return topicService.addTopic(session.getAttribute("user").toString(),talk,talkDetail,courseId);
    }
    @RequestMapping(value = "findtopic",method = RequestMethod.GET)
    public List<Talk> findTalk(String courseId){
        return topicService.findTalk(courseId);
    }
    @RequestMapping(value = "findreply",method = RequestMethod.GET)
    public List<Talk> findReply(String talkId) {
        return topicService.findReply(talkId);
    }
    @RequestMapping(value = "reply",method = RequestMethod.GET)
    public boolean reply(HttpSession session,String topicId,String message){

        return topicService.reply(topicId,message,session.getAttribute("user").toString());
    }
}
