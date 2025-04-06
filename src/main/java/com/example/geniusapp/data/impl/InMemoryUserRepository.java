package com.example.geniusapp.data.impl;

import com.example.geniusapp.core.user.User;
import com.example.geniusapp.data.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(nextId++);
            users.add(user);
        } else {
            users.removeIf(u -> u.getId() == user.getId());
            users.add(user);
        }
        return user;
    }

    @Override
    public void delete(User user) {
        users.removeIf(u -> u.getId() == user.getId());
    }
}