package com.example.kafka;

import com.example.config.ApplicationConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {

    private final ApplicationConfiguration applicationConfiguration;

    @KafkaListener(topics = "user-service", groupId = "group_id")
    public void consume(String message) {
        log.info("#### -> Log Server Properties -> {}", applicationConfiguration.getMessage());
        log.info("#### -> Consumed message -> {}", message);
    }
}
