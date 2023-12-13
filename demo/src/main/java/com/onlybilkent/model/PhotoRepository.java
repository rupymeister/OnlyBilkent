package com.onlybilkent.model;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface PhotoRepository extends MongoRepository<Photo, String> { 
    
}