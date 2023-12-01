package com.onlybilkent.model;

import java.util.ArrayList;

//@Document(clloection='users') bu database kurulduktan sonra kullanılıcak
public class User{
    private String name;
    private String password;
    private String email;
    private String bio;
    private int role;
    private String profilePic;
    private ArrayList<String> notifications = new ArrayList<String>();

    public User(String name, String email, String password, String bio, int role, String profilePic, String notifications){
        setName(name);
        setEmail(email);
        setPassword(password);
        setBio(bio);
        setRole(role);
        setProfilePic(profilePic);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getBio(){
        return this.bio;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

    public int getRole(){
        return this.role;
    }

    public void setRole(int role){
        this.role = role;
    }

    public String getProfilePic(){
        return this.profilePic;
    }

    public void setProfilePic(String profilePic){
        this.profilePic = profilePic;
    }
}

