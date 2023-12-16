package com.onlybilkent.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "chats")

public class Chat {
    @Id
    String chatId;
    
    List<Message> messages;
    String senderId;
    String receiverId;

    public Chat(String senderId, String receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

}


