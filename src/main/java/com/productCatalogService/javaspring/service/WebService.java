package com.productCatalogService.javaspring.service;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebService {
    private final WebClient webClient;
    public WebService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.example.com").build();
    }

    public Mono<String> fetchSomeData() {
        return webClient.get()
                .uri("/endpoint")
                .retrieve()
                .bodyToMono(String.class);
    }
}
