package com.onlybilkent.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    @Autowired
    private PostService postService;

    @Autowired
    public UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;


    public String addPhoto(String title, MultipartFile file) throws IOException { 
        Photo photo = new Photo(title); 
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        photo = photoRepo.insert(photo); 
        return photo.getId(); 
    }

    public void deletePhoto(String id) {
        photoRepo.deleteById(id);
    }

    // write a method to add a photo to a post here
    public String addPhotoToPost(String postId, String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
        Post post = postService.getPost(postId);
        post.setPhotoId(photo.getId());
        postService.savePost(post);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(postId)), new Update().set("photoId", photo.getId()), Post.class);
        return photo.getId();
    }

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepo = photoRepository;
    }

    public Photo getPhoto(String id) {
        return photoRepo.findById(id).get();
    }

    public Optional<Photo> getPhotoById(String id) {
        return photoRepo.findById(id);
    }

    public List<Photo> getPhotos() {
        return photoRepo.findAll();
    }
}