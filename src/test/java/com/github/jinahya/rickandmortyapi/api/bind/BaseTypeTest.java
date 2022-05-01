package com.github.jinahya.rickandmortyapi.api.bind;

class BaseTypeTest<T extends BaseType<T>>
        extends BaseTypeStubTest<T> {

    protected BaseTypeTest(final Class<T> typeClass) {
        super(typeClass);
    }
}