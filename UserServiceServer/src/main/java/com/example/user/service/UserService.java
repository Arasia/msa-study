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

        User findUser = userRepository.findById(user.getId())
                .orElseThrow(IllegalAccessError::new);

        User findByNameUser = userRepository.findByName(user.getName())
                .orElseThrow(IllegalArgumentException::new);

        log.info("#### -> Is Same Test1 : {}", findUser == user);
        log.info("#### -> Is Same Test2 : {}", findByNameUser == user);

        log.info("#### -> Log Server Properties -> {}", applicationConfiguration.getMessage());

        userKafkaService.send("Find User : " + user);

        return Collections.singletonList(UserResponse.of(user));
    }

    public UserResponse saveUser(UserRequest userRequest) {
        User user = userRequest.toUser();

        User persistUser = userRepository.save(user);

        User findUser = userRepository.findById(persistUser.getId())
                .orElseThrow(IllegalAccessError::new);

        User secondFindUser = userRepository.findById(persistUser.getId())
                .orElseThrow(IllegalAccessError::new);

        User findByNameUser = userRepository.findByName(persistUser.getName())
                .orElseThrow(IllegalArgumentException::new);

        log.info("#### -> Is Same Test1 : {}", user == persistUser);
        log.info("#### -> Is Same Test2 : {}", findUser == persistUser);
        log.info("#### -> Is Same Test3 : {}", secondFindUser == persistUser);
        log.info("#### -> Is Same Test4 : {}", findByNameUser == persistUser);

        log.info("#### -> Log Server Properties -> {}", applicationConfiguration.getMessage());

        userKafkaService.send("Create User : " + user);

        return UserResponse.of(persistUser);
    }
}
