package com.github.jinahya.rickandmortyapi.api.bind;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
abstract class BaseTypeStub<T extends BaseTypeStub<T>>
        extends RepresentationModel<T> {

    protected abstract static class BaseTypeStubBuilder<
            T extends BaseTypeStub<T>,
            C extends BaseTypeStub<T>,
            B extends BaseTypeStubBuilder<T, C, B>
            > {

        protected B $fillValuesFrom(C instance) {
            $fillValuesFromInstanceIntoBuilder(instance, this);
            return this.self();
        }

        private static <T extends BaseTypeStub<T>> void $fillValuesFromInstanceIntoBuilder(
                final BaseTypeStub<T> instance, final BaseTypeStubBuilder<T, ?, ?> b) {
        }

        protected abstract B self();

        public abstract C build();
    }

    BaseTypeStub(final BaseTypeStubBuilder<T, ?, ?> b) {
        super();
    }
}
