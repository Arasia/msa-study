package com.example.controller;

import com.example.config.ApplicationConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/welcome")
@Controller
@RequiredArgsConstructor
@Slf4j
public class Welcome {
    private final ApplicationConfiguration applicationConfiguration;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public String welcomePage(@PathVariable String id) {
        log.info("Post id : {}", id);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("msg", id + " welcome" + applicationConfiguration.getMessage());

        var stringInJson = "";

        try {
            var objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringInJson;
    }
}
