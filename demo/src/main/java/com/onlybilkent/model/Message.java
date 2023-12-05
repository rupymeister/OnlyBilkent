package com.onlybilkent.model;

public class Message extends Sendable {
    private User receiver;
    private boolean isSeen;

    
   

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
