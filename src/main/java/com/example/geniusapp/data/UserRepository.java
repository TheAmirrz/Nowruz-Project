package com.example.geniusapp.data;

import com.example.geniusapp.core.user.User;
import java.util.Optional;
import java.util.List;

public interface UserRepository {
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    User save(User user);
    void delete(User user);
    // Add other user-specific methods if needed
}