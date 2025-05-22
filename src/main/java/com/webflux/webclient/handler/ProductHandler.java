package com.webflux.webclient.handler;

import com.webflux.webclient.models.Product;
import com.webflux.webclient.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class ProductHandler {

    private final ProductService productService;

    public Mono<ServerResponse> getProducts(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll(), Product.class);
    }

    public Mono<ServerResponse> getProductDetail(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        return productService.findById(id)
                .flatMap(product ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(product)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        Mono<Product> productMono = serverRequest.bodyToMono(Product.class);
        return productMono.flatMap(product -> {
                    if (product.getCreateAt() == null) {
                        product.setCreateAt(new Date());
                    }

                    return productService.save(product);
                })
                .flatMap(product ->
                        ServerResponse.created(URI.create("/api/client/products".concat(product.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(product))
                .onErrorResume(error -> ServerResponse
                        .badRequest()
                        .bodyValue("Error in the request body or saving the product"));
    }
}
