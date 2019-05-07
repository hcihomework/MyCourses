package com.service;

import com.bean.Talk;

import java.util.List;

public interface TopicService {
    boolean addTopic(String user,String talk,String talkDetail,String courseId);
    List<Talk> findTalk(String courseId);
    List<Talk> findReply(String talkId);
    boolean reply(String topicId,String message,String user);
}
