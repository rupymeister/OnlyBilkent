package com.onlybilkent.model;

public class Sendable {
    public User sender;
    public String type;
    public String content;

    public Sendable(User sender, String type, String content){
        setSender(sender);
        setType(type);
        setContent(content);
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
}
