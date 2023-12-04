package com.onlybilkent.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> allPosts() {
        // Logging statement
        System.out.println(postRepository.findAll().toString());
        return postRepository.findAll();
    }

    public Optional<Post> singlePost(ObjectId id) {
        return postRepository.findById(id);
    }
}
