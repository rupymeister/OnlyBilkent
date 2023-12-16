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
import java.util.List;

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
    private String photoId = "6578c01d1fcb0e1176c52d6d";;
    private int price;
    private int viewCount;
    private boolean isActive;

    private List<String> photoIds;

    private Category category;
    private PostType postType;
    private LocalDate borrowUntilDate; // Use LocalDate for dates
    private double loanPricePerTime;
    private double salePrice;
    private boolean isFree;

    // Constructors

    public Post(String senderId, PostType postType, Category category) {
        this.postType = postType;
        this.senderId = senderId;
        this.category = category;
    }
    
    public Post(String senderId, PostType postType, String photoId, Category category) {
        if (photoId == null || photoId.equals("")) {
            this.photoId = "6578c01d1fcb0e1176c52d6d";
        } else {
            this.photoId = photoId;
        }
        this.postType = postType;
        this.senderId = senderId;
    }
    

    public Post(String title, String content, String senderId, boolean isActive, PostType postType, String photoId, Category category) {
        if (photoId == null || photoId.equals("")) {
            this.photoId = "6578c01d1fcb0e1176c52d6d";
        } else {
            this.photoId = photoId;
        }
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
        this.postType = postType;
    }

    public Post(String title, String content, String senderId, boolean isActive, String photoId, PostType postType, Category category) {
        if (photoId == null || photoId.equals("")) {
            this.photoId = "6578c01d1fcb0e1176c52d6d";
        } else {
            this.photoId = photoId;
        }
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
        this.postType = postType;
    }

    public Post(String title, String content, String senderId, boolean isActive, String photoId, PostType postType, LocalDate borrowUntilDate, double loanPricePerTime, double salePrice, boolean isFree, Category category) {
        if (!(photoId.length() > 5)) {
            this.photoId = "6578c01d1fcb0e1176c52d6d";
        }
        else {
            this.photoId = photoId;
        }
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.isActive = isActive;
        this.postType = postType;
        this.borrowUntilDate = borrowUntilDate;
        this.loanPricePerTime = loanPricePerTime;
        this.salePrice = salePrice;
        this.isFree = isFree;
    }

    // Getters and setters

    public Post(String userId, PostType postType2, String title2, String content2, String photoId2,
            Category category2) {
        if (!(photoId.length() > 5)) {
            this.photoId = "6578c01d1fcb0e1176c52d6d";
        }
        else {
            this.photoId = photoId2;
        }
        this.title = title2;
        this.content = content2;
        this.senderId = userId;
        this.isActive = true;
        this.postType = postType2;
        this.category = category2;
    }

    // Enums for post types
    public enum PostType {
        BORROW,
        LOAN,
        SALE,
        FREE
    }

    enum Category {
        BOOKS,
        ELECTRONICS,
        FURNITURE,
        CLOTHING,
        HOUSING,
        ROOMMATES,
        TEACHING,
        OTHER
    }
}
