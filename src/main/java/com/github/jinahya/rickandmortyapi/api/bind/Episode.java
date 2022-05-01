package com.github.jinahya.rickandmortyapi.api.bind;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Jacksonized
@SuperBuilder(toBuilder = true)
@Slf4j
public class Episode
        extends BaseType<Episode> {

    private Integer id;

    private String name;

    @JsonProperty("air_date")
    private String airDate;

    private String episode;

    private List<String> characters;

    private String url;

    private String created;
}
