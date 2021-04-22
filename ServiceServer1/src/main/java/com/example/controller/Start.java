package com.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/start")
@Controller
public class Start {
    private static Logger logger = LoggerFactory.getLogger(Start.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String StartApi(@PathVariable String id) {
        logger.info("Post id : {}", id);

        String jsonInString = "";

        try {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000);
            factory.setReadTimeout(5000);

            RestTemplate restTemplate = new RestTemplate(factory);

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(headers);

            String url = "http://localhost:9090/welcome/";

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + id).build();

            ResponseEntity<Map> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

            jsonInString = new ObjectMapper().writeValueAsString(responseEntity.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInString;
    }
}
