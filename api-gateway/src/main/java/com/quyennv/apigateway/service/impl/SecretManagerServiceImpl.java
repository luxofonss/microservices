package com.quyennv.apigateway.service.impl;

import com.quyennv.apigateway.service.SecretManagerService;
import com.quyennv.apigateway.constant.SecretKeyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecretManagerServiceImpl implements SecretManagerService {

    @Value("${environment}")
    private String environment;

    @Autowired
    private Map<String, String> googleSecrets;

    @Override
    public String getValue(SecretKeyEnum secretKey) {
        String key = secretKey.getValue();

        String keyEnv = String.format("%s_%s", key, environment);

        if (googleSecrets.containsKey(keyEnv)) {
            return googleSecrets.get(keyEnv);
        }

        return googleSecrets.getOrDefault(key, null);
    }
}
