package com.example.geniusapp.data;

import com.example.geniusapp.core.user.Admin;
import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> findById(int id);
    Optional<Admin> findByUsername(String username);
    List<Admin> findAll();
    Admin save(Admin admin);
    void delete(Admin admin);
}