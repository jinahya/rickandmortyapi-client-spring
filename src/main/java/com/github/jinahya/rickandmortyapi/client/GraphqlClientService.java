package com.github.jinahya.rickandmortyapi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.GraphQlClient;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;
import java.util.function.Function;

public class GraphqlClientService {

    public static final String BASE_URL = "https://rickandmortyapi.com/graphql";

//    /**
//     * A qualifier annotation for an instance of {@link WebClient} connects to {@value #BASE_URL}.
//     */
//    @Qualifier
//    @Documented
//    @Target(value = {ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD,
//            ElementType.PARAMETER})
//    @Retention(RetentionPolicy.RUNTIME)
//    public @interface RickAndMortyApiGraphQlWebClient {
//
//    }

    /**
     * A qualifier annotation for an instance of {@link GraphQlClient} connects to {@value #BASE_URL}.
     */
    @Qualifier
    @Documented
    @Target(value = {ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD,
            ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RickAndMortyApiGraphQlClient {

    }

    /**
     * Creates a new instance with specified web client.
     *
     * @param graphQlClient the web client for accessing {@value #BASE_URL}.
     */
    public GraphqlClientService(final @RickAndMortyApiGraphQlClient @Autowired GraphQlClient graphQlClient) {
        super();
        this.graphQlClient = Objects.requireNonNull(graphQlClient, "graphQlClient is null");
    }

    public <R> R applyGraphQlClient(final @NotNull Function<? super GraphQlClient, ? extends R> function) {
        return function.apply(graphQlClient);
    }

    protected final GraphQlClient graphQlClient;
}
