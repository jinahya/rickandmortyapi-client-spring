package com.github.jinahya.rickandmortyapi.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class RestClientServiceIT {

    @Test
    void readEpisode() {
        restClientService.readEpisode(1)
                .doOnNext(r -> {
                    log.debug("info: {}", r.getInfo());
                    r.getResults().forEach(e -> {
                        log.debug("episode: {}", e);
                    });
                })
                .block();
    }

    @Autowired
    private RestClientService restClientService;
}