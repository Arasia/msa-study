package com.example.apigateway.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    public String getErrorPath() {
        return ERROR_PATH;
    }


    @RequestMapping(ERROR_PATH)
    public ResponseEntity<Map<String, String>> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(status.toString()));

        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("code", status.toString());
        errorMsg.put("msg", httpStatus.getReasonPhrase());


        return ResponseEntity.status(httpStatus)
                .body(errorMsg);
    }
}
