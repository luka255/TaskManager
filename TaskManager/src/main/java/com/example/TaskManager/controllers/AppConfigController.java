package com.example.TaskManager.controllers;

import com.example.TaskManager.Security.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppConfigController {

    @Autowired
    private AppProperties appProperties;

    @GetMapping("/api/config")
    public String getAppConfig() {
        return String.format("App Name: %s, Base URL: %s", appProperties.getAppName(), appProperties.getBaseUrl());
    }
}