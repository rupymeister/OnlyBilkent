package com.onlybilkent.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    private AnnouncementService announcementService;

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

    // write a method to add a photo to an announcement here
    public String addPhotoToAnnouncement(String announcementId, String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
        Announcement announcement = announcementService.getAnnouncement(announcementId);
        announcement.setPhotoId(photo.getId());
        announcementService.saveAnnouncement(announcement);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(announcementId)), new Update().set("photoId", photo.getId()), Announcement.class);
        return photo.getId();
    }

    // write a method to add multiple photos here
    public List<String> addMultiplePhotos(MultipartFile[] images) throws IOException {
        List<String> ids = new ArrayList<>();
        for (MultipartFile file : images) {
            ids.add(addPhoto(file.getOriginalFilename(), file));
        }
        return ids;
    }

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepo = photoRepository;
    }

    public Photo getPhoto(String id) {
        System.out.println("ID received: " + id); // Add this line
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        return photoRepo.findById(id).orElse(null);
    }
    

    public Optional<Photo> getPhotoById(String id) {
        return photoRepo.findById(id);
    }

    public List<Photo> getPhotos() {
        return photoRepo.findAll();
    }

    public String getStringPhoto(String id) {
        return photoRepo.findById(id).get().getImage().toString();
    }
}