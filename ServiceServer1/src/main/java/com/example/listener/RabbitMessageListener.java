package com.example.listener;

import com.example.dto.RabbitCustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMessageListener {
    @RabbitListener(queues = "microservice-a")
    public void receiveMessage(final RabbitCustomMessage message) {
        log.info("{}", message);
    }
}
