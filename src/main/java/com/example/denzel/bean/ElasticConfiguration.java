package com.example.denzel.bean;

import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.denzel.repository.elastic")
@ComponentScan(basePackages = {"com.example.denzel.service"})
public class ElasticConfiguration {

    @Bean
    public RestHighLevelClient client(@Value("${elastic.user}") String login,
                                       @Value("${elastic.password}") String pass,
                                       @Value("${elastic.host}") String host,
                                       @Value("${elastic.port}") String port) {
        final var clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo("http://" + login + ":" + pass + "@" + host + ":" + port).build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(@Qualifier("client") RestHighLevelClient restHighLevelClient) {
        return new ElasticsearchRestTemplate(restHighLevelClient);
    }
}