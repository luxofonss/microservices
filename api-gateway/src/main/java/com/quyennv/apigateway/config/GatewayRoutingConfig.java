package com.quyennv.apigateway.config;

import com.quyennv.apigateway.constant.RouteConfigEnum;
import com.quyennv.apigateway.entity.RouteConfigEntity;
import com.quyennv.apigateway.filters.LoggingRequestFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class GatewayRoutingConfig {

    RouteConfigEntity userServiceRouteConfig;

    @Autowired
    RoutePropertyConfig routePropertyConfig;

    @Autowired
    LoggingRequestFilter loggingRequestFilter;

    @PostConstruct
    public void init() {
        log.info("Init GatewayRoutingConfig {}", routePropertyConfig.getRouteConfigMap());
        userServiceRouteConfig = routePropertyConfig.getRouteConfigMap().get(RouteConfigEnum.USER_SERVICE.getKey());
    }

    @Bean
    RouteLocator routing(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(RouteConfigEnum.USER_SERVICE.getKey(), r -> r
                        .path(userServiceRouteConfig.getPath())
                        .filters(f -> f
                                .cacheRequestBody(Object.class)
                                .filter(loggingRequestFilter.apply(new LoggingRequestFilter.Config()))
                                .rewritePath(userServiceRouteConfig.getRegexPath(), userServiceRouteConfig.getReplacementPath())
                        )
                        .uri(userServiceRouteConfig.getUri()))
                .build();
    }

}
