package com.example.listener;

import com.example.dto.RabbitCustomMessage2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessageListener {
    Logger logger = LoggerFactory.getLogger(RabbitMessageListener.class);

    @RabbitListener(queues = "microservice-a")
    public void receiveMessage(final RabbitCustomMessage2 message) {
        logger.info("{}", message);
    }
}
