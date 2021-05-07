package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/rabbit")
@Controller
public class RabbitMessageProducer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMessageProducer.class);

    private static final String TOPIC_EXCHANGE_NAME = "microservice-a-exchange";
    private static final String ROUTING_KEY = "foo.bar.#";

    private final RabbitTemplate rabbitTemplate;

    public RabbitMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(value = "/send")
    @ResponseBody
    public String rabbitSend() {
        logger.info("Start RabbitSend");

        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY, "RabbitSendMessage");

        return "success";
    }
}
