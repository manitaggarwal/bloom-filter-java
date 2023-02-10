package com.manitaggarwal.bloom.controller;

import com.manitaggarwal.bloom.controller.request.CreateUserRequest;
import com.manitaggarwal.bloom.repository.User;
import com.manitaggarwal.bloom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{username}", produces = "application/json")
    private User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    private User createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }
}
