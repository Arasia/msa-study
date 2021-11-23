package com.example.user.dto;

import com.example.user.domain.User;

public class UserRequest {

    private String name;

    protected UserRequest() {
    }

    private UserRequest(String name) {
        this.name = name;
    }

    public static UserRequest of(String name) {
        return new UserRequest(name);
    }

    public String getName() {
        return name;
    }

    public User toUser() {
        return User.of(name);
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
