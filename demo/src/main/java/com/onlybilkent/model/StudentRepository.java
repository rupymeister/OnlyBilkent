package com.onlybilkent.model;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    List<Student> findByCanPostTrue();

    Optional<Student> findById(ObjectId id);

    Student save(Student student);
}