package com.example.geniusapp.data.impl;

import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.data.ArtistRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseArtistRepository implements ArtistRepository {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public DatabaseArtistRepository(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public Optional<Artist> findById(int id) {
        // Implement database logic
        return Optional.empty();
    }

    @Override
    public Optional<Artist> findByUsername(String username) {
        // Implement database logic
        return Optional.empty();
    }

    @Override
    public List<Artist> findAll() {
        // Implement database logic
        return new ArrayList<>();
    }

    @Override
    public Artist save(Artist artist) {
        // Implement database logic
        return null;
    }

    @Override
    public void delete(Artist artist) {
        // Implement database logic
    }

    @Override
    public List<Artist> findAllFollowedBy(int userId) {
        // Implement database logic
        return new ArrayList<>();
    }
}