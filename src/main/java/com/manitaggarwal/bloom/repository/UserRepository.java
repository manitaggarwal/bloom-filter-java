package com.manitaggarwal.bloom.repository;

import com.manitaggarwal.bloom.entiry.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
