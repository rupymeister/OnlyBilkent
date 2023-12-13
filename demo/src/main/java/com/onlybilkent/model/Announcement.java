package com.onlybilkent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Document(collection = "announcements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {

    @Id
    private String announcementID;

    
    private String title;
    private String photoId;
    private String content;
    private String senderID;
    private LocalDate date;

    public Announcement(String senderID, String title, String content) {
        this.title = title;
        this.content = content;
        this.senderID = senderID;
        this.photoId = "6578c01d1fcb0e1176c52d6d";
        this.announcementID = new ObjectId().toString();
        this.date = LocalDate.now();
    }

    public Announcement(String title, String content, String senderID, String photoId) {
        this.title = title;
        this.content = content;
        this.senderID = senderID;
        if (photoId == null || photoId.equals("")) {
            this.photoId = "6578c01d1fcb0e1176c52d6d";
        } else {
            this.photoId = photoId;
        }
        this.announcementID = new ObjectId().toString();
        this.date = LocalDate.now();
    }

    public String getSenderId() {
        return senderID;
    }

}
