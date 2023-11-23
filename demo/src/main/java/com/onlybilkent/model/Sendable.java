package com.onlybilkent.model;

public class Sendable {
    public User sender;
    public String type;
    public String content;
    public long id;

    public Sendable(User sender, String type, String content, long id){
        setSender(sender);
        setType(type);
        setContent(content);
        setId(id);
    }

    public void setSender(User sender){
        this.sender = sender;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setContent(String content){
        this.content = content;
    }
     
    public void setId(long id){
        this.id = id;
    }
}
