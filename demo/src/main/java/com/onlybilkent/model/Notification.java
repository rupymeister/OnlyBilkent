package com.onlybilkent.model;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Document(collection = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @Field("notificationId") // Specify the field name as "notificationId"
    private String notificationId;
    private boolean isRead = false;
    private String content;
    private LocalDate date;
    private String userId;

    public Notification(String userId, String content) {
        this.userId = userId;
        this.content = content;
        this.notificationId = new ObjectId().toString();
        this.date = LocalDate.now();
    }

}
