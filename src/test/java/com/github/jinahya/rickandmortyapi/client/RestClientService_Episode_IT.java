package com.github.jinahya.rickandmortyapi.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
@Slf4j
class RestClientService_Episode_IT
        extends ApiClientServiceIT {

    @DisplayName("readEpisodes(page)")
    @ValueSource(ints = {0, 1, 2})
    @ParameterizedTest
    void readEpisodes__(final int page) {
        restClientService().readEpisodes(page)
                .doOnNext(r -> {
                    log.debug("info: {}", r.getInfo());
                    r.getResults().forEach(c -> {
                        log.debug("episode: {}", c);
                    });
                })
                .block();
    }

    @Test
    void readAllEpisodes__() {
        restClientService().readAllEpisodes()
                .flatMap(r -> Flux.fromIterable(r.getResults()))
                .doOnNext(c -> {
                    log.debug("episode: {}", c);
                })
                .blockLast();
    }

    @DisplayName("readEpisode(id)")
    @ValueSource(ints = {1, 2, 3})
    @ParameterizedTest
    void readEpisode__(final int id) {
        restClientService().readEpisode(id)
                .doOnNext(c -> {
                    log.debug("episode: {}", c);
                })
                .block();
    }

    @DisplayName("readEpisodes(1, 2, 3)")
    @Test
    void readEpisodes__() {
        restClientService().readEpisodes(1, 2, 3)
                .doOnNext(l -> {
                    l.forEach(c -> {
                        log.debug("episode: {}", c);
                    });
                })
                .block();
    }
}