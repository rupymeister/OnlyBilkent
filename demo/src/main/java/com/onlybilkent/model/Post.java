package com.onlybilkent.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private boolean isActive;

    private byte[] imageData; // Field for storing local image data (if applicable)

    public Post(String title, String content, String senderId, boolean isActive) {
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
    }

    public Post(String title, String content, String senderId, boolean isActive, byte[] imageData) {
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
        this.imageData = imageData;
    }

}
