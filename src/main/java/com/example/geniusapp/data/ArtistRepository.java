package com.example.geniusapp.data;

import com.example.geniusapp.core.user.Artist;
import java.util.Optional;
import java.util.List;

public interface ArtistRepository {
    Optional<Artist> findById(int id);
    Optional<Artist> findByUsername(String username);
    List<Artist> findAll();
    Artist save(Artist artist);
    void delete(Artist artist);
    List<Artist> findAllFollowedBy(int userId);
    // Add other artist-specific methods if needed
}