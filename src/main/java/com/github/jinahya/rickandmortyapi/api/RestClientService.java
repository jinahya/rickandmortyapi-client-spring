package com.github.jinahya.rickandmortyapi.api;

import com.github.jinahya.rickandmortyapi.api.bind.BaseType;
import com.github.jinahya.rickandmortyapi.api.bind.Character;
import com.github.jinahya.rickandmortyapi.api.bind.Episode;
import com.github.jinahya.rickandmortyapi.api.bind.Location;
import com.github.jinahya.rickandmortyapi.api.bind.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

@Service
@Slf4j
public class RestClientService {

    public static final String BASE_URL = "https://rickandmortyapi.com/api";

    /**
     * A qualifier annotation for an instance of {@link WebClient}.
     *
     * @see #BASE_URL
     */
    @Qualifier
    @Documented
    @Target(value = {ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD,
            ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RickAndMortyApiRestWebClient {

    }

    /**
     * Creates a new instance uses specified web client.
     *
     * @param webClient the web client.
     */
    public RestClientService(final @RickAndMortyApiRestWebClient @Autowired(required = false) WebClient webClient) {
        super();
        this.webClient = Optional.ofNullable(webClient)
                .orElseGet(() -> WebClient.builder().baseUrl(BASE_URL).build());
    }

    public <T extends BaseType<T>> Mono<Response<T>> readResponse(
            final @NotBlank String path, final @Positive @Nullable Integer page,
            final @NotNull ParameterizedTypeReference<Response<T>> elementTypeRef) {
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.path(path).queryParamIfPresent("page", Optional.ofNullable(page)).build())
                .retrieve()
                .bodyToMono(elementTypeRef);
    }

    public Mono<Response<Character>> readCharacter(final @Positive @Nullable Integer page) {
        return readResponse("/character", page, new ParameterizedTypeReference<>() {
        });
    }

    public Mono<Response<Episode>> readEpisode(final @Positive @Nullable Integer page) {
        return readResponse("/episode", page, new ParameterizedTypeReference<>() {
        });
    }

    public Mono<Response<Location>> readLocation(final @Positive @Nullable Integer page) {
        return readResponse("/location", page, new ParameterizedTypeReference<>() {
        });
    }

    protected final WebClient webClient;
}
