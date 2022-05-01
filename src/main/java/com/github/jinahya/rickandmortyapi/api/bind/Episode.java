package com.github.jinahya.rickandmortyapi.api.bind;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Jacksonized
@SuperBuilder(toBuilder = true)
@Slf4j
public class Episode
        extends BaseType<Episode> {

    private static final String AIR_DATE_PATTERN = "MMMM d, uuuu";

    public static final DateTimeFormatter AIR_DATE_FORMATTER
            = DateTimeFormatter.ofPattern(AIR_DATE_PATTERN).withLocale(Locale.US);

    @JsonIgnore
    public LocalDate getAirDateAsLocalDate() {
        return Optional.ofNullable(getAirDate())
                .map(v -> LocalDate.parse(v, AIR_DATE_FORMATTER))
                .orElse(null);
    }

    private Integer id;

    private String name;

    @JsonProperty("air_date")
    private String airDate;

    private String episode;

    private List<String> characters;

    private String url;

    private ZonedDateTime created;
}
