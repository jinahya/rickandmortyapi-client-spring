package com.github.jinahya.rickandmortyapi.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
class RestClientServiceConfiguration {

    @RestClientService.RickAndMortyApiRestWebClient
    @Bean
    WebClient webClient() {
        final WebClient webClient1 = WebClient.builder()
                .baseUrl(RestClientService.BASE_URL)
                .build();
        log.debug("webClient1: {}", webClient1);
        return webClient1;
    }
}
