package com.example.TaskManager.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Value("${custom.app-name}")
    private String appName;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${api.baseUrl}")
    private String baseUrl;

    public String getAppName() {
        return appName;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
