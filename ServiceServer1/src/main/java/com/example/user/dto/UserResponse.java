package com.example.user.dto;

import com.example.user.domain.User;

public class UserResponse {

    private Long id;
    private String name;

    protected UserResponse() {
    }

    private UserResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
