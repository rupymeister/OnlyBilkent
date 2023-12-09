package com.onlybilkent.model;


import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "messages")
@AllArgsConstructor
@NoArgsConstructor
public class Message{
    private ObjectId receiverId;
    private ObjectId senderId;
    private ObjectId messageId;
    private String content;

    public Message(String senderId, String receiverId, String content){
        this.senderId = isValidHexString(senderId) ? new ObjectId(senderId) : null;
        this.receiverId = isValidHexString(receiverId) ? new ObjectId(receiverId) : null;
        this.messageId = new ObjectId();
        this.content = content;
    }
    private boolean isValidHexString(String hexString) {
        // Validate hex string length
        return hexString != null && hexString.length() == 24 && hexString.matches("\\p{XDigit}+");
    }
    public String getContent() {
        return content;
    }
}
