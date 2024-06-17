package com.quyennv.apigateway.service;

import com.quyennv.apigateway.constant.SecretKeyEnum;

public interface SecretManagerService {
    String getValue(SecretKeyEnum secretKey);
}
