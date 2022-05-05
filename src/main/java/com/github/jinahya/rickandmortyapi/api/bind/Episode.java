package com.github.jinahya.rickandmortyapi.api.bind;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Jacksonized
@SuperBuilder(toBuilder = true)
@Slf4j
public class Episode
        extends BaseType<Episode> {

    private static final long serialVersionUID = 4681902269254116911L;

    private static final String AIR_DATE_PATTERN = "MMMM d, uuuu";

    private static final DateTimeFormatter AIR_DATE_FORMATTER
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
