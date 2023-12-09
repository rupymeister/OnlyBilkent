package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {

    Optional<Post> findPostByTitle(String title);

    boolean existsById(String postId);

    Post findByPostId(String postId);

    Optional<Post> findBySenderId(String senderId);

    Optional<Post> findByTitleRegex(String str);
    Optional<Post> findByContentRegex(String str);

}