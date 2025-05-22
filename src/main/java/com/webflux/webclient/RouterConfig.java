package com.webflux.webclient;

import com.webflux.webclient.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> routes(ProductHandler productHandler){
        return RouterFunctions.route()
                .GET("/api/client/products", productHandler::getProducts)
                .GET("/api/client/products/{id}", productHandler::getProductDetail)
                .build();
    }
}
