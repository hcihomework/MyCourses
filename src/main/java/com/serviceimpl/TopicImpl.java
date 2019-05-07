package com.serviceimpl;

import com.bean.Talk;
import com.entity.Forum;
import com.repository.ForumRepository;
import com.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TopicImpl implements TopicService {
    @Autowired
    private ForumRepository forumRepository;
    private static TopicImpl topicImpl;
    @PostConstruct
    public void init(){
        topicImpl=this;
        topicImpl.forumRepository=this.forumRepository;
    }
    @Override
    public boolean addTopic(String user, String talk, String talkDetail, String courseId) {
        Forum forum=new Forum();

        List<Forum> forumList=topicImpl.forumRepository.findAll();
        String topicId="0";
        if(forumList!=null&&forumList.size()!=0){
            topicId=forumList.size()+"";
        }
        forum.setId(topicId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date=simpleDateFormat.format(new Date());
        forum.setTime(date);

        forum.setCourse_id(courseId);
        forum.setMessage(talkDetail);
        forum.setFrom_id(user);
        forum.setTo_id("null");
        forum.setTalk(talk);

        try{
            topicImpl.forumRepository.save(forum);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<Talk> findTalk(String courseId) {
        List<Talk> talkList=new ArrayList<>();
        List<Forum> forumList=topicImpl.forumRepository.findTalk(courseId);
        if(forumList!=null&&forumList.size()!=0){
            for(int i=0;i<forumList.size();i++){
                Talk talk=new Talk();
                talk.setId(forumList.get(i).getId());
                talk.setTopic(forumList.get(i).getTalk());
                talk.setTime(forumList.get(i).getTime());
                talk.setUser(forumList.get(i).getFrom_id());
                talk.setMessage(forumList.get(i).getMessage());
                talkList.add(talk);
            }
        }
        return talkList;
    }

    @Override
    public List<Talk> findReply(String talkId) {
        List<Talk> talkList=new ArrayList<>();
        List<Forum> forumList=topicImpl.forumRepository.findReply(talkId);
        if(forumList!=null){
            //System.out.println(forumList.size());
            for(int i=0;i<forumList.size();i++){
                Talk talk=new Talk();
                talk.setId(forumList.get(i).getId());
                talk.setUser(forumList.get(i).getFrom_id());
                talk.setTopic(forumList.get(i).getTalk());
                talk.setTime(forumList.get(i).getTime());
                talk.setMessage(forumList.get(i).getMessage());
                talkList.add(talk);
            }
        }
        return talkList;
    }

    @Override
    public boolean reply(String topicId, String message, String user) {
        Forum forum=new Forum();
        List<Forum> forumList=topicImpl.forumRepository.findAll();
        String replyId="0";
        if(forumList!=null&&forumList.size()!=0){
            replyId=forumList.size()+"";
        }
        forum.setId(replyId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date=simpleDateFormat.format(new Date());
        forum.setTime(date);
        forum.setMessage(message);
        forum.setTo_id(topicId);
        forum.setFrom_id(user);

        try{
            topicImpl.forumRepository.save(forum);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
