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
import org.springframework.web.multipart.MultipartFile;
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
    private String imageId;

    private int role;
    private boolean emailVerified;
    private String emailVerificationToken;

    private ArrayList<String> notifications = new ArrayList<String>();
    private int postCount;

    @DocumentReference
    private List<Post> postId;
    
    @DocumentReference
    private List<User> chatterId;

    public User(String name, String surname, String email, String password, String bio, int role, String imageId, String emailVerificationToken) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.role = role;
        this.imageId = imageId;
        emailVerified = false;
        this.emailVerificationToken = emailVerificationToken;
        this.postCount = 0;
    }

}
