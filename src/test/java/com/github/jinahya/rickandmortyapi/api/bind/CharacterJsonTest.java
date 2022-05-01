package com.github.jinahya.rickandmortyapi.api.bind;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;

@Slf4j
class CharacterJsonTest
        extends BaseTypeJsonTest<Character> {

    CharacterJsonTest() {
        super(Character.class);
    }

    @Test
    void character_json__() throws IOException {
        try (var stream = getClass().getResourceAsStream("character.json")) {
            assert stream != null;
            final ObjectContent<Response<Character>> content = responseJacksonTester().read(stream);
            final Response<Character> object = content.getObject();
            log.debug("info: {}", object.getInfo());
            object.getResults().forEach(r -> {
                log.debug("character: {}", r);
            });
        }
    }
}
