package com.github.jinahya.rickandmortyapi.api.bind;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Jacksonized
@SuperBuilder(toBuilder = true)
@Slf4j
// https://rickandmortyapi.com/documentation/#character-schema
public class Character
        extends BaseType<Character> {

    public enum Status {

        ALIVE("Alive"),

        DEAD("Dead"),

        @JsonEnumDefaultValue
        UNKNOWN("unknown");

        Status(final String jsonValue) {
            this.jsonValue = Objects.requireNonNull(jsonValue, "jsonValue is null");
        }

        @JsonValue
        private final String jsonValue;
    }

    public enum Gender {

        FEMALE("Female"),

        MALE("Male"),

        GENDERLESS("Genderless"),

        @JsonEnumDefaultValue
        UNKNOWN("unknown");

        Gender(final String jsonValue) {
            this.jsonValue = Objects.requireNonNull(jsonValue, "jsonValue is null");
        }

        @JsonValue
        private final String jsonValue;
    }

    @NotNull
    private Integer id;

    private String name;

    private Status status;

    private String species;

    private String type;

    private Gender gender;

    private ObjectNode origin;

    private ObjectNode location;

    @URL
    private String image;

    private List<@URL @NotBlank String> episode;

    private String url;

    private ZonedDateTime created;
}
