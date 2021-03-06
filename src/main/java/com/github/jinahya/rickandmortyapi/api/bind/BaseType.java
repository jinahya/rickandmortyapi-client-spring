package com.github.jinahya.rickandmortyapi.api.bind;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(Character.class),
        @JsonSubTypes.Type(Episode.class),
        @JsonSubTypes.Type(Info.class),
        @JsonSubTypes.Type(Location.class),
        @JsonSubTypes.Type(Response.class)
})
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Slf4j
public abstract class BaseType<T extends BaseType<T>>
        extends BaseTypeStub<T> {

    protected Map<String, Object> getUnknownProperties() {
        if (unknownProperties == null) {
            unknownProperties = new HashMap<>();
        }
        return unknownProperties;
    }

    @JsonAnySetter
    protected void putUnknownProperty(final String key, final Object value) {
        getUnknownProperties().put(key, value);
    }

    private Map<String, Object> unknownProperties;
}
