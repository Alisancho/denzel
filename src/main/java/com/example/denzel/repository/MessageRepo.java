package com.example.denzel.repository;

import com.example.denzel.entity.security.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface MessageRepo extends ReactiveCrudRepository<Message, Long> {
}