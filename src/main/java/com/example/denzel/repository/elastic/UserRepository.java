package com.example.denzel.repository.elastic;

import com.example.denzel.entity.elastic.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {
}
