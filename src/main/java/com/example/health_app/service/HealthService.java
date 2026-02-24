package com.example.health_app.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${custom.config-value}")
    private String configValue;

    public Map<String, Object> getHealthDetails() {
        Map<String, Object> response = new HashMap<>();

        response.put("applicationName", appName);
        response.put("timestamp", LocalDateTime.now());
        response.put("buildVersion",
                System.getenv().getOrDefault("BUILD_VERSION", "NOT_SET"));
        response.put("configValue", configValue);

        return response;
    }
}
