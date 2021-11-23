package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.domain.UserRepository;
import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return Collections.singletonList(UserResponse.of(user));
    }

    public UserResponse saveUser(UserRequest userRequest) {
        User user = userRequest.toUser();

        User persistUser = userRepository.save(user);

        return UserResponse.of(persistUser);
    }
}
