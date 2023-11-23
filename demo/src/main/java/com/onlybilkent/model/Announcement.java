package com.onlybilkent.model;

public class Announcement extends Sendable{
    private String announcementID;
    private String title;
    private String image; 

    public Announcement(User sender, String type, String content, long id, String announcementID, String title, String iamge){
        super(sender, type, content, id);
        setAnnouncementID(announcementID);
        setTitle(title);
        setImage(image);
    }

    public void setAnnouncementID(String announcementID){
        this.announcementID = announcementID;
    }

    public String getAnnouncementID(){
        return this.announcementID;
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

}
