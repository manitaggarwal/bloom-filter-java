package com.manitaggarwal.bloom.service;

import com.manitaggarwal.bloom.controller.request.CreateUserRequest;
import com.manitaggarwal.bloom.repository.User;

public interface UserService {
    User getUserByUsername(String username);

    User createUser(CreateUserRequest createUserRequest);
}
