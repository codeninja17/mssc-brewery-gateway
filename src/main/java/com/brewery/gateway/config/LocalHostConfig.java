package com.brewery.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local-discovery")
@Configuration
public class LocalHostConfig {

    @Bean
    public RouteLocator googleRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("beer-service", "/api/v1/**")
                        .uri("http://localhost:8080"))
                .route(r -> r.path("beer-order-service", "/api/v1/customers/**")
                        .uri("http://localhost:8081"))
                .route(r -> r.path("beer-inventory-service", "/api/v1/*/inventory")
                        .uri("http://localhost:8082"))
                .build();
    }

}
