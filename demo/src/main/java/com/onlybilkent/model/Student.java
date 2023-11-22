package com.onlybilkent.model;

public class Student extends User{

    private boolean canPost;

    public Student(String name, String email, String password, String bio, int role, String profilePic, String notifications, String messages, String posts, boolean canPost){
        super(name, email, password, bio, role, profilePic, notifications);
        setCanPost(true);
        //list messgaes, posts
    }

    public void setCanPost(boolean canPost){
        this.canPost = canPost;
    }

    public boolean getCanPost(){
        return this.canPost;
    }
} 
