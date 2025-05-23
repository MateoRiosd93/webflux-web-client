package com.webflux.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfig {

    @Value("${config.base.endpoint}")
    private String BASE_URL;

    @Bean
    @LoadBalanced
    public WebClient.Builder registerWebClient(){
        // Al utilizar el LoadBalanced se agrega el .Builder al metodo y se cambia el return
        // return WebClient.create(BASE_URL);
        return WebClient.builder().baseUrl(BASE_URL);
    }
}
