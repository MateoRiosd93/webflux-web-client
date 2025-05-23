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

    // Al implementar Eureka en el WebClient usaremos el .Builder y webClient.build() en las implementaciones de los metodos.
    private final WebClient.Builder webClient;

    @Override
    public Flux<Product> findAll() {
        return webClient.build().get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Mono<Product> findById(String id) {
        return webClient.build().get()
                .uri("/{id}", Collections.singletonMap("id", id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<Product> save(Product product) {
        return webClient.build().post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<Product> edit(Product product, String id) {
        return webClient.build().put()
                .uri("/{id}", Collections.singletonMap("id", id))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<Void> delete(String id) {
        return webClient.build().delete()
                .uri("/{id}", Collections.singletonMap("id", id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
