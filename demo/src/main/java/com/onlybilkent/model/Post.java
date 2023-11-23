package com.onlybilkent.model;

public class Post extends Sendable{
    private String category;
    private String title;
    private String image;

    public Post(User sender, String type, String content, long id, String category, String title, String image){
        super(sender, type, content, id);
        setCategory(category);
        setTitle(title);
        setImage(image);
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getImage(){
        return this.image;
    } 

    public long getId(){
        return this.id;
    }
}
