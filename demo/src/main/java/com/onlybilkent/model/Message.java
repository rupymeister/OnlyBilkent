package com.onlybilkent.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bson.types.ObjectId;

@Document(collection = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Message {

    @Id
    private ObjectId messageId;

    private ObjectId receiverId;
    private ObjectId senderId;

    private boolean isRead = false;
    private String content;
    private LocalDate date;
    private LocalTime time;

    public Message(String senderId, String receiverId, String content) {
        this.senderId = isValidHexString(senderId) ? new ObjectId(senderId) : null;
        this.receiverId = isValidHexString(receiverId) ? new ObjectId(receiverId) : null;
        this.messageId = new ObjectId();
        this.content = content;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.isRead = false;
    }

    private boolean isValidHexString(String hexString) {
        // Validate hex string length
        return hexString != null && hexString.length() == 24 && hexString.matches("\\p{XDigit}+");
    }

}
