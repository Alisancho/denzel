package com.example.denzel.service;

import com.example.denzel.entity.elastic.ElasticType;
import com.example.denzel.entity.elastic.User;
import com.example.denzel.repository.elastic.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ElasticServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public CompletableFuture<Void> save(final ElasticType elasticClass) {
        return CompletableFuture.runAsync(() -> {
            if (elasticClass instanceof User user) {
                userRepository.save(user);
            }
        });
    }
}
