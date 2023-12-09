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
    private String surname;
    private String password;
    private String email;
    private String bio;
    private String profilePic;

    private int role;
    private boolean emailVerified;
    private String emailVerificationToken;

    private ArrayList<String> notifications = new ArrayList<String>();

    @DocumentReference
    private List<Post> postId;

    public User(String name, String surname, String email, String password, String bio, int role, String profilePic,
            String emailVerificationToken) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.role = role;
        this.profilePic = profilePic;
        emailVerified = false;
        this.emailVerificationToken = emailVerificationToken;
    }

}
