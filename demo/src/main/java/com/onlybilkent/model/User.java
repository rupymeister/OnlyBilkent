package com.onlybilkent.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.bson.types.ObjectId;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {
    @Id
    private ObjectId id;
    private String name;
    private String password;
    private String email;

    private int role;

    private ArrayList<String> notifications = new ArrayList<String>();

    @DocumentReference
    private List<Post> postId;

    public User(String name, String email, String password, String bio, int role, String profilePic) {
        this.name = name;
        this.email = email;
        this.password = password;
        // this.bio = bio;
        this.role = role;
        // this.profilePic = profilePic;
    }

    

}
