package com.manitaggarwal.bloom.service;

import com.manitaggarwal.bloom.controller.request.CreateUserRequest;
import com.manitaggarwal.bloom.repository.User;
import com.manitaggarwal.bloom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        // check in bloom filter
        // database call
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        // check if the username is taken
        // saving user to database
        return userRepository.save(
                new User(createUserRequest.username(), createUserRequest.msisdn()));
    }
}
