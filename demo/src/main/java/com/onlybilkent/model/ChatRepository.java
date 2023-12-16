package com.onlybilkent.model;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {

    Optional<Chat> findByReceiverId(String receiverId);

    Optional<Chat> findBySenderId(String senderId);

    Optional<Chat> findByChatId(String chatId);

    boolean existsBySenderIdAndReceiverId(String userId, String receiverId);

}