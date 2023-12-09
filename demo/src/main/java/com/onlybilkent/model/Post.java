package com.onlybilkent.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.bson.types.ObjectId;

@Document(collection = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private String senderId;

    private boolean isActive;

    // Constructor without id
    public Post(String title, String content, String senderId, boolean isActive) {
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
    }

}
