package com.github.jinahya.rickandmortyapi.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import reactor.core.publisher.Flux;

@Slf4j
class RestClientService_Character_IT
        extends ApiClientServiceIT {

    @DisplayName("readCharacters(page)")
    @ValueSource(ints = {0, 1, 2})
    @ParameterizedTest
    void readCharacters__(final int page) {
        restClientService().readCharacters(page)
                .doOnNext(r -> {
                    log.debug("info: {}", r.getInfo());
                    r.getResults().forEach(c -> {
                        log.debug("character: {}", c);
                    });
                })
                .block();
    }

    @Test
    void readAllCharacters__() {
        restClientService().readAllCharacters()
                .flatMap(r -> Flux.fromIterable(r.getResults()))
                .doOnNext(c -> {
                    log.debug("character: {}", c);
                })
                .blockLast();
    }

    @DisplayName("readCharacter(id)")
    @ValueSource(ints = {1, 2, 3})
    @ParameterizedTest
    void readCharacter__(final int id) {
        restClientService().readCharacter(id)
                .doOnNext(c -> {
                    log.debug("character: {}", c);
                })
                .block();
    }

    @DisplayName("readCharacters(1, 2, 3)")
    @Test
    void readCharacters__() {
        restClientService().readCharacters(1, 2, 3)
                .doOnNext(l -> {
                    l.forEach(c -> {
                        log.debug("character: {}", c);
                    });
                })
                .block();
    }
}