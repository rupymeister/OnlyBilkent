package com.onlybilkent.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

@Service
public class PostService {

    @Autowired
    public PostRepository postRepository;

    public List<Post> allPosts() {
        return postRepository.findAll();
    }

}