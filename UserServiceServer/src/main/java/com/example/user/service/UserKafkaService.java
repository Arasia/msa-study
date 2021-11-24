package com.example.user.service;

import com.example.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserKafkaService {

    private static final String KAFKA_TOPIC = "user-service";

    private final KafkaProducer kafkaProducer;

    public void send(String message) {
        kafkaProducer.sendMessage(KAFKA_TOPIC, message);
    }
}
