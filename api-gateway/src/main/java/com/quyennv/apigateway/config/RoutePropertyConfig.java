package com.quyennv.apigateway.config;

import com.quyennv.apigateway.entity.RouteConfigEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "route", ignoreUnknownFields = false, ignoreInvalidFields = false)
@Getter
@Setter
public class RoutePropertyConfig {

    private Map<String, RouteConfigEntity> routeConfigMap;

}
