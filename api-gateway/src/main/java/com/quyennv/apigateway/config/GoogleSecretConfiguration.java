package com.quyennv.apigateway.config;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import com.quyennv.apigateway.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@Slf4j
public class GoogleSecretConfiguration {
    @Value("${google.secret-manager.project-id}")
    private String projectId;

    @Value("${google.secret-manager.secret-id}")
    private String secretId;

    @Value("${google.secret-manager.version}")
    private String version;

    @Bean
    @SneakyThrows
    public Map<String, String> googleSecrets() {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {

            SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, version);
            AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);
            String payload = response.getPayload().getData().toStringUtf8();
            return JsonUtils.readObject(payload, Map.class);
        }
    }
}
