package com.example.controller;

import com.example.config.ApplicationConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RequestMapping(value = "/start")
@Controller
@RequiredArgsConstructor
@Slf4j
public class Start {
    private final ApplicationConfiguration applicationConfiguration;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public String startApi(@PathVariable String id) {
        log.info("Post id : {}", id);

        var jsonInString = "";

        try {
            var factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000);
            factory.setReadTimeout(5000);

            var restTemplate = new RestTemplate(factory);

            var headers = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(headers);

            var url = "http://localhost:9090/welcome/";

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + id).build();

            ResponseEntity<Map> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

            Map<String, String> resultMap = responseEntity.getBody();

            assert (resultMap != null);
            resultMap.put("message", applicationConfiguration.getMessage());

            jsonInString = new ObjectMapper().writeValueAsString(resultMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInString;
    }
}
