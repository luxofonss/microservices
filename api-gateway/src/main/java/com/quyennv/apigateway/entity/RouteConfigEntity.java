package com.quyennv.apigateway.entity;

import lombok.Data;

@Data
public class RouteConfigEntity {

    private String path;

    private String regexPath;

    private String replacementPath;

    private String uri;

}
