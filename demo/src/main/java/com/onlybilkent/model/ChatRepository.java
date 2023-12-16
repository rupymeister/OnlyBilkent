package com.onlybilkent.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {

    Chat findByReceiverId(String receiverId);

    Chat findBySenderId(String senderId);

    Chat findByChatId(String chatId);

    boolean existsBySenderIdAndReceiverId(String chatId, String receiverId);

    List<Message> getMessagesByChatId(String chatId);

}
