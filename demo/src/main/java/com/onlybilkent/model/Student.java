package com.onlybilkent.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class Student extends User {

    private boolean canPost;
    private List<Post> posts;

    public Student(String name, String email, String password, String bio, int role, String profilePic, String notifications, String messages, String posts, boolean canPost){
        super(name, email, password, bio, role, profilePic, notifications);
        setCanPost(true);
        this.posts = new ArrayList<>();
    }

    public void setCanPost(boolean canPost){
        this.canPost = canPost;
    }
    // test anıl
    public boolean getCanPost(){
        return this.canPost;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
} 
