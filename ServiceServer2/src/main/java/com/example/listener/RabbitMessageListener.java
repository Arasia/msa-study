package com.example.listener;

import com.example.dto.RabbitCustomMessage2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMessageListener {
    @RabbitListener(queues = "microservice-a")
    public void receiveMessage(final RabbitCustomMessage2 message) {
        log.info("{}", message);
    }
}
