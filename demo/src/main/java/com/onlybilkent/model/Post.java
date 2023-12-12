package com.onlybilkent.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.bson.types.ObjectId;

import java.time.LocalDate;

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
    private String photoId;
    private int price;
    private int viewCount;
    private boolean isActive;

    private PostType postType;
    private LocalDate borrowUntilDate; // Use LocalDate for dates
    private double loanPricePerTime;
    private double salePrice;
    private boolean isFree;

    // Constructors
    public Post(String senderId, PostType postType) {
        this.postType = postType;
        this.senderId = senderId;
    }
    public Post(String title, String content, String senderId, boolean isActive, PostType postType) {
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
        this.postType = postType;
    }

    public Post(String title, String content, String senderId, boolean isActive, String photoId, PostType postType) {
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
        this.photoId = photoId;
        this.postType = postType;
    }

    // Getters and setters

    // Enums for post types
    public enum PostType {
        BORROW,
        LOAN,
        SALE,
        FREE
    }
}
