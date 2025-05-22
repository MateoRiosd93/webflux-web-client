package com.webflux.webclient.services;

import com.webflux.webclient.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<Product> save(Product product);
    Mono<Product> edit(Product product, String id);
    Mono<Void> delete(Product product);
}
