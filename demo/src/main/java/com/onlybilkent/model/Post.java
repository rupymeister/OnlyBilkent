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
public class Post extends Sendable {
    private String category;
    private String title;
    private String image;

    public Post(User sender, String type, String content, ObjectId id, String category, String title, String image) {
        super(sender, type, content, id);
        setCategory(category);
        setTitle(title);
        setImage(image);
    }

    // Getters and setters

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    
}
