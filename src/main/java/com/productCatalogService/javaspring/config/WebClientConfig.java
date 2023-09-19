package com.productCatalogService.javaspring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Slf4j
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
        .baseUrl("https://api.example.com")
        .defaultHeader("Authorization", "Bearer YourAccessToken")
        .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                .compress(true)
                .responseTimeout(Duration.ofSeconds(10))))
        .filter((request, next) -> {
            log.info("Sending request: {} {}", request.method(), request.url());
            return next.exchange(request);
        });
    }
}
