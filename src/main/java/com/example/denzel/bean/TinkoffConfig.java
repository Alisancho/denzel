package com.example.denzel.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApiFactory;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Slf4j
//@Configuration
public class TinkoffConfig {
//    @Bean
//    public OpenApi getOpenApi(@Value("${tinkoff.invest.token.prod}") String token) {
//        return new OkHttpOpenApiFactory(token, Logger.getLogger("OPEN_API_LOG"))
//                .createOpenApiClient(Executors.newFixedThreadPool(2));
//    }
}
