package com.onlybilkent.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {

    User findByReceiverId(String receiverId);

    User findBySenderId(String senderId);

    Optional<Chat> findByChatId(String chatId);

    List<Message> getMessagesByUserId(String userId);

    String getSenderId(String chatId);

}
