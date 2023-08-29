package com.example.javaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class JavaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringApplication.class, args);
    }

}
