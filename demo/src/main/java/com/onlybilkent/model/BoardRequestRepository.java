package com.onlybilkent.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRequestRepository extends MongoRepository<BoardRequest, String> {

    BoardRequest findByUserId(String userId);

    boolean existsByUserId(String userId);

    List<BoardRequest> findAll();

}
