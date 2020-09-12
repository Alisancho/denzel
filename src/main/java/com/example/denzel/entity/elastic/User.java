package com.example.denzel.entity.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "blog")
public class User {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private int age;
}