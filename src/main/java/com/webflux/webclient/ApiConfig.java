package com.webflux.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfig {

    @Value("${config.base.endpoint}")
    private String BASE_URL;

    @Bean
    public WebClient registerWebClient(){
        return WebClient.create(BASE_URL);
    }
}
