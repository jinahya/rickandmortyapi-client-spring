package com.github.jinahya.rickandmortyapi.api.bind;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
abstract class BaseTypeJsonTest<T extends BaseType<T>>
        extends BaseTypeTest<T> {

    protected BaseTypeJsonTest(final Class<T> typeClass) {
        super(typeClass);
    }

    @Autowired
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private JacksonTester<T> jacksonTester;

    @Autowired
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private JacksonTester<Response<T>> responseJacksonTester;
}