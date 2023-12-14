package com.onlybilkent.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardRequestService {

    @Autowired
    private BoardRequestRepository boardRequestRepository;

    public BoardRequest findByUserId(String userId) {
        return boardRequestRepository.findByUserId(userId);
    }

    public boolean existsByUserId(String userId) {
        return boardRequestRepository.existsByUserId(userId);
    }

    public List<BoardRequest> findAll() {
        return boardRequestRepository.findAll();
    }

}
