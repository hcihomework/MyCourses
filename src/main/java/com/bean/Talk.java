package com.bean;

public class Talk {
    private String id;
    private String topic;
    private String time;
    private String user;
    private String message;
    public Talk(){}
    public Talk(String id,String topic,String time,String user,String message){
        this.id=id;
        this.topic=topic;
        this.time=time;
        this.user=user;
        this.message=message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
