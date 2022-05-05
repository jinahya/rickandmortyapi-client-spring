module com.github.jinahya.rickandmortyapi.client.spring {
    requires com.fasterxml.jackson.databind;
    requires java.validation;
    requires lombok;
    requires org.hibernate.validator;
    requires org.slf4j;
    requires reactor.core;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.hateoas;
    requires spring.web;
    requires spring.webflux;

    opens com.github.jinahya.rickandmortyapi;
    opens com.github.jinahya.rickandmortyapi.api;
    opens com.github.jinahya.rickandmortyapi.api.bind;

    exports com.github.jinahya.rickandmortyapi.api;
    exports com.github.jinahya.rickandmortyapi.api.bind;
}