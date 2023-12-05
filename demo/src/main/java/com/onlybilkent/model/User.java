package com.onlybilkent.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
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

    private int role;

    private ArrayList<String> notifications = new ArrayList<String>();

    @DocumentReference
    private List<Post> postIds;

    public User(String name, String email, String password, String bio, int role, String profilePic){
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.role = role;
        this.profilePic = profilePic;
    }

    public ObjectId getId(){
        return this._id;
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


    public int getRole(){
        return this.role;
    }

    public void setRole(int role){
        this.role = role;
    }



}

