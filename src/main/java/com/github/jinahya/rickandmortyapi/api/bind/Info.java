package com.github.jinahya.rickandmortyapi.api.bind;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Jacksonized
@SuperBuilder(toBuilder = true)
@Slf4j
public class Info
        extends BaseType<Info> {

    private static final long serialVersionUID = 3717808769834037367L;

    @PositiveOrZero
    private int count;

    @PositiveOrZero
    private int pages;

    @URL
    private String next;

    @URL
    private String prev;
}
