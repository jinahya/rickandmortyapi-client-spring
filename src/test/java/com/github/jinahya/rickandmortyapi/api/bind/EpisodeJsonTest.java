package com.github.jinahya.rickandmortyapi.api.bind;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;

@Slf4j
class EpisodeJsonTest
        extends BaseTypeJsonTest<Episode> {

    EpisodeJsonTest() {
        super(Episode.class);
    }

    @Test
    void episode_json__() throws IOException {
        try (var stream = getClass().getResourceAsStream("episode.json")) {
            assert stream != null;
            final ObjectContent<Response<Episode>> content = responseJacksonTester().read(stream);
            final Response<Episode> object = content.getObject();
            log.debug("info: {}", object.getInfo());
            object.getResults().forEach(r -> {
                log.debug("episode: {}", r);
            });
        }
    }
}
