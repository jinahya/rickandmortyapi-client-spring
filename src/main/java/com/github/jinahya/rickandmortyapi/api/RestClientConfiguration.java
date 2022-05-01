package com.github.jinahya.rickandmortyapi.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
class RestClientConfiguration {

    public static final String BASE_URL = "https://rickandmortyapi.com/api";

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
