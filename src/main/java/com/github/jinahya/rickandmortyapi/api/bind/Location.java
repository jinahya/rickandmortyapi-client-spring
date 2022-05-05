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

import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Jacksonized
@SuperBuilder(toBuilder = true)
@Slf4j
public class Location
        extends BaseType<Location> {

    private static final long serialVersionUID = 1780598600419814611L;

    private Integer id;

    private String name;

    private String type;

    private String dimension;

    private List<String> residents;

    private String url;

    private ZonedDateTime created;
}
