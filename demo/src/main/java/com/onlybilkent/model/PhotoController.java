package com.onlybilkent.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Base64;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.parser.PartTree.OrPart;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photos")
public class PhotoController {
    
    @Autowired
    private PhotoService photoService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // write a method to get all photos here
    @GetMapping("/allPhotos")
    public List<Photo> getPhotos() {
        return photoService.getPhotos();
    }

    // write a method to get a photo here
    @GetMapping("/getPhoto/{photoId}")
    public Photo getPhoto(@PathVariable String photoId) {
        return photoService.getPhoto(photoId);
    }

    //write a method to get picture of a post here
    @GetMapping("/getPostPhoto/{postId}")
    public String getPostPhoto(@PathVariable String postId, Model model) {
        Post post = postService.getPost(postId);
        Photo photo = photoService.getPhoto(post.getPhotoId());
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image", 
        Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return post.getPhotoId();
    }

    @PostMapping("/addPhoto")
    public String addPhoto(@RequestParam("image") MultipartFile image) throws IOException {
        String id = photoService.addPhoto(image.getOriginalFilename(),image);
        return id;
    }

    // write add a photo to a post here
    @PostMapping("/addPhotoToPost")
    public String addPhotoToPost(@RequestParam("image") MultipartFile image, @RequestParam String postId) throws IOException {
        String id = photoService.addPhotoToPost(postId, image.getOriginalFilename(),image);
        return id;
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
    
        if (photo != null) {
            Resource resource = new ByteArrayResource(photo.getPhoto().getData());
    
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getTitle() + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            // Handle the case where the photo is not found (optional is empty)
            return ResponseEntity.notFound().build();
        }
    }
    

    //write a method to edit profile picture here
    @PutMapping("/editProfilePicture/{userId}")
    public ResponseEntity<User> editProfilePicture(@RequestParam("image") MultipartFile image, @PathVariable String userId) throws IOException {
        User user = userService.getUser(userId);
        String id = photoService.addPhoto(image.getOriginalFilename(),image);
        user.setProfilePictureId(id);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
