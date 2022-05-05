package com.github.jinahya.rickandmortyapi.client;

import com.github.jinahya.rickandmortyapi.client.bind.Character;
import com.github.jinahya.rickandmortyapi.client.bind.Episode;
import com.github.jinahya.rickandmortyapi.client.bind.Location;
import com.github.jinahya.rickandmortyapi.client.bind.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Positive;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Slf4j
public class RestClientService {

    /**
     * The base url for REST. The value is {@value}.
     *
     * @see <a href="https://rickandmortyapi.com/documentation/#rest">REST (rickandmortyapi.com)</a>
     */
    public static final String BASE_URL = "https://rickandmortyapi.com/api";

    private static final String PARAM_PAGE = "page";

    /**
     * A qualifier annotation for an instance of {@link WebClient} connects to {@value #BASE_URL}.
     */
    @Qualifier
    @Documented
    @Target(value = {ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD,
            ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RickAndMortyApiRestWebClient {

    }

    /**
     * Creates a new instance with specified web client.
     *
     * @param webClient the web client for accessing {@value #BASE_URL}.
     */
    public RestClientService(final @RickAndMortyApiRestWebClient @Autowired(required = false) WebClient webClient) {
        super();
        this.webClient = Optional.ofNullable(webClient)
                .orElseGet(() -> WebClient.builder().baseUrl(BASE_URL).build());
    }

    /**
     * Reads characters of specified page.
     *
     * @param page a value for {@code ?page} parameter; {@code null} for the first page.
     * @return a page of characters.
     * @see <a href="https://rickandmortyapi.com/documentation/#get-all-characters">Get all characters
     * (rickandmortyapi.com)</a>
     */
    public Mono<Response<Character>> readCharacters(final @Positive @Nullable Integer page) {
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.path("/character").queryParamIfPresent(PARAM_PAGE, Optional.ofNullable(page)).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Reads all pages of all characters.
     *
     * @return a flux of all pages of characters.
     * @see #readCharacters(Integer)
     */
    public Flux<Response<Character>> readAllCharacters() {
        final AtomicInteger page = new AtomicInteger(1);
        return readCharacters(page.getAndIncrement())
                .expand(r -> Optional.ofNullable(r.getInfo().getNext()).map(n -> page.getAndIncrement())
                        .map(this::readCharacters)
                        .map(m -> m.flatMapMany(Flux::just))
                        .orElseGet(Flux::empty));
    }

    /**
     * Reads a single character identified by specified value.
     *
     * @param id the id of the character.
     * @return an identified character
     * @see <a href="https://rickandmortyapi.com/documentation/#get-a-single-character">Get a single character</a>
     */
    public Mono<Character> readCharacter(final int id) {
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.pathSegment("character", "{id}").build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Reads multiple characters identified by specified values.
     *
     * @param id       an id of a character.
     * @param otherIds more ids of characters.
     * @return an identified character
     * @see <a href="https://rickandmortyapi.com/documentation/#get-multiple-characters">Get multiple characters</a>
     */
    public Mono<List<Character>> readCharacters(final int id, final int... otherIds) {
        final String ids = IntStream.concat(IntStream.of(id), Stream.ofNullable(otherIds).flatMapToInt(IntStream::of))
                .distinct()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.pathSegment("character", "{ids}").build(ids))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Reads episodes of specified page.
     *
     * @param page a value for {@code ?page} parameter; {@code null} for the first page.
     * @return a page of episodes.
     * @see <a href="https://rickandmortyapi.com/documentation/#get-all-episodes">Get all episodes</a>
     * (rickandmortyapi.com)
     */
    public Mono<Response<Episode>> readEpisodes(final @Positive @Nullable Integer page) {
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.path("/episode").queryParamIfPresent(PARAM_PAGE, Optional.ofNullable(page)).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Reads all pages of all episodes.
     *
     * @return a flux of all pages of episodes.
     * @see #readEpisodes(Integer)
     */
    public Flux<Response<Episode>> readAllEpisodes() {
        final AtomicInteger page = new AtomicInteger(1);
        return readEpisodes(page.getAndIncrement())
                .expand(r -> Optional.ofNullable(r.getInfo().getNext()).map(n -> page.getAndIncrement())
                        .map(this::readEpisodes)
                        .map(m -> m.flatMapMany(Flux::just))
                        .orElseGet(Flux::empty));
    }

    /**
     * Reads a single episode identified by specified value.
     *
     * @param id the id of the episode.
     * @return an identified episode
     * @see <a href="https://rickandmortyapi.com/documentation/#get-a-single-episode">Get a single episode</a>
     */
    public Mono<Episode> readEpisode(final int id) {
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.pathSegment("episode", "{id}").build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Reads multiple episodes identified by specified values.
     *
     * @param id       an id of a episode.
     * @param otherIds more ids of episodes.
     * @return an identified episode
     * @see <a href="https://rickandmortyapi.com/documentation/#get-multiple-episodes">Get multiple episodes</a>
     */
    public Mono<List<Episode>> readEpisodes(final int id, final int... otherIds) {
        final String ids = IntStream.concat(IntStream.of(id), Stream.ofNullable(otherIds).flatMapToInt(IntStream::of))
                .distinct()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.pathSegment("episode", "{ids}").build(ids))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Reads locations of specified page.
     *
     * @param page a value for {@code ?page} parameter; {@code null} for the first page.
     * @return a page of locations.
     * @see <a href="https://rickandmortyapi.com/documentation/#get-all-locations">Get all locations</a>
     * (rickandmortyapi.com)
     */
    public Mono<Response<Location>> readLocations(final @Positive @Nullable Integer page) {
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.path("/location").queryParamIfPresent(PARAM_PAGE, Optional.ofNullable(page)).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Reads all pages of all locations.
     *
     * @return a flux of all pages of locations.
     * @see #readLocations(Integer)
     */
    public Flux<Response<Location>> readAllLocations() {
        final AtomicInteger page = new AtomicInteger(1);
        return readLocations(page.getAndIncrement())
                .expand(r -> Optional.ofNullable(r.getInfo().getNext()).map(n -> page.getAndIncrement())
                        .map(this::readLocations)
                        .map(m -> m.flatMapMany(Flux::just))
                        .orElseGet(Flux::empty));
    }

    /**
     * Reads a single location identified by specified value.
     *
     * @param id the id of the location.
     * @return an identified location
     * @see <a href="https://rickandmortyapi.com/documentation/#get-a-single-location">Get a single location</a>
     */
    public Mono<Location> readLocation(final int id) {
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.pathSegment("location", "{id}").build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Reads multiple locations identified by specified values.
     *
     * @param id       an id of a location.
     * @param otherIds more ids of locations.
     * @return an identified location
     * @see <a href="https://rickandmortyapi.com/documentation/#get-multiple-locations">Get multiple locations</a>
     */
    public Mono<List<Location>> readLocations(final int id, final int... otherIds) {
        final String ids = IntStream.concat(IntStream.of(id), Stream.ofNullable(otherIds).flatMapToInt(IntStream::of))
                .distinct()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        return webClient
                .method(HttpMethod.GET)
                .uri(b -> b.pathSegment("location", "{ids}").build(ids))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    protected final WebClient webClient;
}
