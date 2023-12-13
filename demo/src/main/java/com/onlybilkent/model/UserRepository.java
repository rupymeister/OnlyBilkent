package com.onlybilkent.model;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// If your field in the User entity is named userEmail, your repository method should be named findByUserEmail. Ours is email so findByEmail.
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findById(String id);

    Optional<User> findByEmail(String email);

    boolean existsById(String userId);

    Optional<User> findByEmailVerificationToken(String emailVerificationToken);

}