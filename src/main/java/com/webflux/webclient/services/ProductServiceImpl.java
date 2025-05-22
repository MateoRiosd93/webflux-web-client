package com.webflux.webclient.services;

import com.webflux.webclient.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final WebClient webClient;

    @Override
    public Flux<Product> findAll() {
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Mono<Product> findById(String id) {
        return webClient.get()
                .uri("/{id}", Collections.singletonMap("id", id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<Product> save(Product product) {
        return webClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<Product> edit(Product product, String id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Product product) {
        return null;
    }
}
