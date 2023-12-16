package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlybilkent.model.Post.Category;
import com.onlybilkent.model.Post.PostType;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {

    boolean existsById(String postId);

    Post findById(String postId);

    List<Post> findBySenderId(String senderId);

    @Query("{ 'title' : { '$regex' : ?0 , $options: 'i'}}")
    List<Post> findByTitleRegex(String str);
    
    @Query("{ 'content' : { '$regex' : ?0 , $options: 'i'}}")
    List<Post> findByContentRegex(String str);

    List<Post> findByCategory(Category category);

    List<Post> findByPostType(PostType postType);
    
    List<Post> findByActive(boolean active);

    List<Post> findByBorrowUntilDate(LocalDate borrowUntilDate);

    List<Post> findByPriceLessThanEqual(int price);

    List<Post> findByPriceGreaterThanEqual(int price);

    List<Post> findByPriceBetween(int minPrice, int maxPrice);

    @Override 
    <S extends Post> S save(S entity);
}
