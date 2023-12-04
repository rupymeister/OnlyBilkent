package com.onlybilkent.model;

import java.util.ArrayList;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import org.bson.types.ObjectId;

@Document(collection="users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User{
    @Id
    private ObjectId _id;
    private String name;
    private String password;
    private String email;
    private String bio;
    private int role;
    private String profilePic;
    private ArrayList<String> notifications = new ArrayList<String>();

    public User(String name, String email, String password, String bio, int role, String profilePic){
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.role = role;
        this.profilePic = profilePic;
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

