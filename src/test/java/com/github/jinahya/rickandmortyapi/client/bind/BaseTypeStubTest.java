package com.github.jinahya.rickandmortyapi.client.bind;

import com.github.jinahya.rickandmortyapi.client.bind.BaseTypeStub;

import java.util.Objects;

abstract class BaseTypeStubTest<T extends BaseTypeStub<T>> {

    protected BaseTypeStubTest(final Class<T> typeClass) {
        super();
        this.typeClass = Objects.requireNonNull(typeClass, "typeClass is null");
    }

    protected final Class<T> typeClass;
}