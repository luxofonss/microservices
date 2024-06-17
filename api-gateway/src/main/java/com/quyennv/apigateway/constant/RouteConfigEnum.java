package com.quyennv.apigateway.constant;

public enum RouteConfigEnum {
    USER_SERVICE("user-svc");

    private String key;

    RouteConfigEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
