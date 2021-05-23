package com.example.controller;

import com.example.dto.RabbitCustomMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/rabbit")
@Controller
@RequiredArgsConstructor
@Slf4j
public class RabbitMessageProducer {
    private static final String TOPIC_EXCHANGE_NAME = "microservice-a-exchange";
    private static final String ROUTING_KEY = "foo.bar.#";

    private final RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/send")
    @ResponseBody
    public String rabbitSend() {
        log.info("Start RabbitSend");

        var msg = new RabbitCustomMessage("message1", 1, true);

        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY, msg);

        return "success";
    }
}
