package com.github.jinahya.rickandmortyapi.api.bind;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;

@Slf4j
class LocationJsonTest
        extends BaseTypeJsonTest<Location> {

    LocationJsonTest() {
        super(Location.class);
    }

    @Test
    void location_json__() throws IOException {
        try (var stream = getClass().getResourceAsStream("location.json")) {
            assert stream != null;
            final ObjectContent<Response<Location>> content = responseJacksonTester().read(stream);
            final Response<Location> object = content.getObject();
            log.debug("info: {}", object.getInfo());
            object.getResults().forEach(r -> {
                log.debug("location: {}", r);
            });
        }
    }
}
