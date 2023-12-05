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
    private ObjectId _id;

    private String title;
    private String content;
    private String senderId;
    
    // Getters and setters

    

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    
}
