package com.manitaggarwal.bloom.service;

import com.manitaggarwal.bloom.controller.request.CreateUserRequest;
import com.manitaggarwal.bloom.entiry.User;
import com.manitaggarwal.bloom.repository.UserRepository;
import com.manitaggarwal.bloom.utils.BloomUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BloomUtils bloomUtils;

    @Override
    public User getUserByUsername(String username) {
        // database call
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    @PostConstruct
    private void postConstruct() {
        // repopulating bloom filter after application starts
        userRepository.findAll().forEach(user ->
                bloomUtils.saveToBloomFilter(user.getUsername()));
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        // check if the username is taken in bloom filter
        if (bloomUtils.checkInBloomFilter(createUserRequest.username())) {
            // check if username is taken in database
            if (userRepository.findUserByUsername(createUserRequest.username()).isPresent()) {
                throw new RuntimeException("User already exists.");
            }
        }
        // save to bloom filter
        bloomUtils.saveToBloomFilter(createUserRequest.username());
        // saving user to database
        return userRepository.save(
                new User(createUserRequest.username(), createUserRequest.msisdn()));

    }
}
