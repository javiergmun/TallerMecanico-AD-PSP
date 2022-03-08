package com.taller2dam.taller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class ApiConfig {

    // Versión de la Api y versión del path, tomados de application.properties
    @Value("${api.path}.path")
    public static final String API_PATH = "/rest";
    @Value("${api.version}")
    public static final String API_VERSION = "1.0";

}