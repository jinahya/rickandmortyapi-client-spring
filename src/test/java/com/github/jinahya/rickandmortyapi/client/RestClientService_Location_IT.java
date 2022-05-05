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
class RestClientService_Location_IT
        extends ApiClientServiceIT {

    @DisplayName("readLocations(page)")
    @ValueSource(ints = {0, 1, 2})
    @ParameterizedTest
    void readLocations__(final int page) {
        restClientService().readLocations(page)
                .doOnNext(r -> {
                    log.debug("info: {}", r.getInfo());
                    r.getResults().forEach(c -> {
                        log.debug("location: {}", c);
                    });
                })
                .block();
    }

    @Test
    void readAllLocations__() {
        restClientService().readAllLocations()
                .flatMap(r -> Flux.fromIterable(r.getResults()))
                .doOnNext(c -> {
                    log.debug("location: {}", c);
                })
                .blockLast();
    }

    @DisplayName("readLocation(id)")
    @ValueSource(ints = {1, 2, 3})
    @ParameterizedTest
    void readLocation__(final int id) {
        restClientService().readLocation(id)
                .doOnNext(c -> {
                    log.debug("location: {}", c);
                })
                .block();
    }

    @DisplayName("readLocations(1, 2, 3)")
    @Test
    void readLocations__() {
        restClientService().readLocations(1, 2, 3)
                .doOnNext(l -> {
                    l.forEach(c -> {
                        log.debug("location: {}", c);
                    });
                })
                .block();
    }
}