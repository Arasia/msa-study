package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableEurekaClient
@SpringBootApplication
public class ServiceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceServerApplication.class, args);
    }
}
