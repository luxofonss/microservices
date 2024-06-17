package com.quyennv.apigateway.filters;

import com.quyennv.apigateway.constant.CommonConstant;
import com.quyennv.apigateway.utils.JsonUtils;
import com.quyennv.apigateway.utils.LoggingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.entries;

@Component
@Slf4j
public class LoggingRequestFilter extends AbstractGatewayFilterFactory<LoggingRequestFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            Map<String, Object> mapCustomizeLog = new HashMap<>();

            mapCustomizeLog.put("request_uri", request.getURI().toString());
            mapCustomizeLog.put("request_method", request.getMethod().toString());
            mapCustomizeLog.put("request_path", request.getPath().toString());
            mapCustomizeLog.put("message_type", "request");
            mapCustomizeLog.put("request_id", exchange.getAttribute(CommonConstant.REQUEST_ID));
            mapCustomizeLog.put("headers", JsonUtils.printObjectToLog(request.getHeaders()));

            Object requestBody = exchange.getAttribute("cachedRequestBody");

            log.info("Request URL: {}", request.getURI().toString());
            log.info("Request URL Path: {}", request.getPath().toString());
            log.info("Request URL HOST: {}", request.getURI().getHost());

            log.info(LoggingUtils.redactSensitiveData(JsonUtils.toJson(requestBody)), entries(mapCustomizeLog));

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        ServerHttpResponse response = exchange.getResponse();
                        log.info("Response_id {} Status Code: {}", exchange.getAttribute(CommonConstant.REQUEST_ID), response.getStatusCode());
                    }));

        };
    }

    public static class Config {
        // Put the configuration properties here
    }
}
