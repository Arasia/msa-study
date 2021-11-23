package com.example.user.controller;

import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping(path = "/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createLine(@RequestBody UserRequest userRequest) {
        log.info(userRequest.toString());

        UserResponse userResponse = userService.saveUser(userRequest);
        return ResponseEntity.created(URI.create("/users/" + userResponse.getId()))
                .body(userResponse);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> showLinesById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(userService.findById(id));
    }
}
