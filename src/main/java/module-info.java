module com.github.jinahya.rickandmortyapi.client {
    requires com.fasterxml.jackson.databind;
    requires java.validation;
    requires lombok;
    requires org.hibernate.validator;
    requires org.reactivestreams;
    requires org.slf4j;
    requires reactor.core;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.graphql;
    requires spring.hateoas;
    requires spring.web;
    requires spring.webflux;

    opens com.github.jinahya.rickandmortyapi.client;
    opens com.github.jinahya.rickandmortyapi.client.bind;

    exports com.github.jinahya.rickandmortyapi.client;
    exports com.github.jinahya.rickandmortyapi.client.bind;
}