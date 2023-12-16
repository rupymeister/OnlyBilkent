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

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String bio;
    private String imageId;

    private int role;
    private boolean emailVerified;
    private String emailVerificationToken;

    private boolean boardRequest = false;
    private boolean banned = false;

    private ArrayList<String> notifications = new ArrayList<String>();
    private int postCount;

    @DocumentReference
    private List<Chat> chatId;

    @DocumentReference
    private List<Post> postId;

    public User(String name, String surname, String email, String password, String bio, int role, String imageId,
            String emailVerificationToken) {
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
        this.notifications = new ArrayList<String>();
        this.postId = new ArrayList<Post>();
    }

    public void setProfilePictureId(String id2) {
        this.imageId = id2;
    }

    enum Role {
        ADMIN, STUDENT, ALUMNI, BOARD, OTHER // other 0, student 1, alumni 2, board 3, admin 4
    }

}
