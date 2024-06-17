package com.quyennv.apigateway.constant;

public enum HeaderEnum {
    X_API_KEY("x-api-key"),
    X_API_SECRET("x-api-secret"),
    X_REQUEST_ID("x-request-id"),
    X_USER_ID("x-user-id");

    private String key;

    HeaderEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
