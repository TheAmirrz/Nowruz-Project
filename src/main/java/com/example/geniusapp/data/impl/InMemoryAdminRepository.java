package com.example.geniusapp.data.impl;

import com.example.geniusapp.core.user.Admin;
import com.example.geniusapp.data.AdminRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryAdminRepository implements AdminRepository {
    private final List<Admin> admins = new ArrayList<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public Optional<Admin> findById(int id) {
        return admins.stream()
                .filter(admin -> admin.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        return admins.stream()
                .filter(admin -> admin.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public List<Admin> findAll() {
        return new ArrayList<>(admins);
    }

    @Override
    public Admin save(Admin admin) {
        if (admin.getId() == 0) {
            admin.setId(nextId.getAndIncrement());
            admins.add(admin);
        } else {
            admins.removeIf(a -> a.getId() == admin.getId());
            admins.add(admin);
        }
        return admin;
    }

    @Override
    public void delete(Admin admin) {
        admins.removeIf(a -> a.getId() == admin.getId());
    }
}