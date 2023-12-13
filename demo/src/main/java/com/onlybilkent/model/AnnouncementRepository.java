package com.onlybilkent.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnnouncementRepository extends MongoRepository<Photo, String>{
    
}
