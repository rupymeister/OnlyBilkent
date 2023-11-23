package com.onlybilkent.model;

public class Message extends Sendable {
    private User receiver;
    private boolean isSeen;

    
    public Message(User sender, String type, String content, long id, User receiver, boolean isSeen){
        super(sender, type, content, id);
        setReceiver(receiver);
        setIsSeen(isSeen);
    }

    public void setReceiver(User receiver){
        this.receiver = receiver;
    }

    public User getReceiver(){
        return this.receiver;
    }

    public void setIsSeen(boolean isSeen){
        this.isSeen = isSeen;
    }

    public boolean getIsSeen(){
        return this.isSeen;
    }
    
}
