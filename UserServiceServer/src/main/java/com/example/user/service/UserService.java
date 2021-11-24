package com.example.user.service;

import com.example.config.ApplicationConfiguration;
import com.example.user.domain.User;
import com.example.user.domain.UserRepository;
import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final ApplicationConfiguration applicationConfiguration;
    private final UserRepository userRepository;
    private final UserKafkaService userKafkaService;

    @Transactional(readOnly = true)
    public List<UserResponse> findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        log.info("#### -> Log Server Properties -> {}", applicationConfiguration.getMessage());

        userKafkaService.send("Find User : " + user);

        return Collections.singletonList(UserResponse.of(user));
    }

    public UserResponse saveUser(UserRequest userRequest) {
        User user = userRequest.toUser();

        User persistUser = userRepository.save(user);

        log.info("#### -> Log Server Properties -> {}", applicationConfiguration.getMessage());

        userKafkaService.send("Create User : " + user);

        return UserResponse.of(persistUser);
    }
}
