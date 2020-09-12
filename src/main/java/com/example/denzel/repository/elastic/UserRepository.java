package com.example.denzel.repository.elastic;

import akka.NotUsed;
import akka.stream.Materializer;
import akka.stream.alpakka.elasticsearch.ElasticsearchWriteSettings;
import akka.stream.alpakka.elasticsearch.WriteMessage;
import akka.stream.alpakka.elasticsearch.javadsl.ElasticsearchFlow;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static akka.stream.alpakka.elasticsearch.ApiVersion.V7;

@Service
public class UserRepository {

    @Autowired
    private Materializer materializer;
    @Autowired
    private RestClient restClient;

    public void deded() throws ExecutionException, InterruptedException {
        List<WriteMessage<Book, NotUsed>> requests =
                Arrays.asList(
                        WriteMessage.createIndexMessage("00001", new Book("Book 1")),
                        WriteMessage.createUpsertMessage("00002", new Book("Book 2")),
                        WriteMessage.createUpsertMessage("00003", new Book("Book 3")),
                        WriteMessage.createUpdateMessage("00004", new Book("Book 4")),
                        WriteMessage.createDeleteMessage("00002"));

        Source.from(requests)
                .via(
                        ElasticsearchFlow.create(
                                "sink8",
                                "_doc",
                                ElasticsearchWriteSettings.create().withApiVersion(V7),
                                restClient,
                                new ObjectMapper()))
                .runWith(Sink.seq(), materializer)
                .toCompletableFuture()
                .get();
    }

    public static class Book {
        public String title;

        public Book() {
        }

        public Book(String title) {
            this.title = title;
        }
    }
}
