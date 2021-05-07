package com.example.controller;

import com.example.config.ApplicationConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/welcome")
@Controller
public class Welcome {
    private static final Logger logger = LoggerFactory.getLogger(Welcome.class);

    ApplicationConfiguration applicationConfiguration;

    public Welcome(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public String welcomePage(@PathVariable String id) {
        logger.info("Post id : {}", id);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("msg", id + " welcome" + applicationConfiguration.getMessage());

        var stringInJson = "";

        try {
            var objectMapper = new ObjectMapper();
            stringInJson = objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringInJson;
    }
}
