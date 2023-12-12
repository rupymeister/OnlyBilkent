package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {

    Optional<Post> findPostByTitle(String title);

    boolean existsById(String postId);

    Post findById(String postId);

    Optional<Post> findBySenderId(String senderId);

    @Query("{ 'title' : { '$regex' : ?0 , $options: 'i'}}")
    Optional<Post> findByTitleRegex(String str);
    
    @Query("{ 'content' : { '$regex' : ?0 , $options: 'i'}}")
    Optional<Post> findByContentRegex(String str);

    @Query("{ 'price' : { '$regex' : ?0 , $options: 'i'}}")
    Optional<Post> findByPriceRegex(int price);

    

    @Override 
    <S extends Post> S save(S entity);
    
    @Override
    Optional<Post> findById(ObjectId postId);

}
