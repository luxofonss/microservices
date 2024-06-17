package com.quyennv.apigateway.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecretKeyEnum {
    USER_SERVICE_API_KEY("USER_SERVICE_API_KEY"),
    USER_SERVICE_API_SECRET("USER_SERVICE_API_SECRET");

    private final String value;
}
