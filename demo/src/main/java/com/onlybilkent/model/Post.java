package com.onlybilkent.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.Multipart;

import org.bson.types.ObjectId;

@Document(collection = "posts")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private String senderId;
    private String photo;

    private boolean isActive;

    private byte[] imageData; // Field for storing local image data (if applicable)
    private MultipartFile image;

    public Post(String title, String content, String senderId, boolean isActive) {
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
    }

    public Post(String title, String content, String senderId, boolean isActive, MultipartFile image) {
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
        this.image = image;
    }

}
