package com.onlybilkent.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {

    List<Chat> findByReceiverId(String receiverId);

    List<Chat> findBySenderId(String senderId);

    Chat findByChatId(String chatId);

    boolean existsBySenderIdAndReceiverId(String chatId, String receiverId);

    List<Message> getMessagesByChatId(String chatId);

}
