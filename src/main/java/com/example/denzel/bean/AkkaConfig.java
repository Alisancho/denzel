package com.example.denzel.bean;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.alpakka.elasticsearch.ElasticsearchSourceSettings;
import akka.stream.alpakka.elasticsearch.ElasticsearchWriteSettings;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.http.HttpHost;

import static akka.stream.alpakka.elasticsearch.ApiVersion.V7;

@Configuration
public class AkkaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("akka-spring-demo");
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
                .initialize(applicationContext);
        return system;
    }
//    @Bean
//    public ActorSystem getActorSystem() {
//        return ActorSystem.create("akka-spring-demo");
//    }

    @Bean
    public Materializer getMaterializer() {
        return ActorMaterializer.create(actorSystem());
    }

    @Bean
    public RestClient getRestClient(@Value("${elastic.user}") String login,
                                    @Value("${elastic.password}") String pass,
                                    @Value("${elastic.host}") String host,
                                    @Value("${elastic.port}") Integer port) {
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(login, pass));

        return RestClient.builder(
                new HttpHost(host, port))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider)).build();
//        return org.elasticsearch.client.RestClient.builder(new HttpHost("localhost", 9200)).build();
    }

    @Bean
    public ElasticsearchSourceSettings getElasticsearchSourceSettings() {
        return ElasticsearchSourceSettings.create();
    }

    @Bean
    public ElasticsearchWriteSettings getElasticsearchWriteSettings() {
        return ElasticsearchWriteSettings.create().withApiVersion(V7);
    }
}
