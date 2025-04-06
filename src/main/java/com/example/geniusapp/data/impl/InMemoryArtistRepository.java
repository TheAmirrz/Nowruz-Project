package com.example.geniusapp.data.impl;

import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.data.ArtistRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryArtistRepository implements ArtistRepository {
    private final List<Artist> artists = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Optional<Artist> findById(int id) {
        return artists.stream().filter(artist -> artist.getId() == id).findFirst();
    }

    @Override
    public Optional<Artist> findByUsername(String username) {
        return artists.stream().filter(artist -> artist.getUsername().equals(username)).findFirst();
    }

    @Override
    public List<Artist> findAll() {
        return new ArrayList<>(artists);
    }

    @Override
    public Artist save(Artist artist) {
        if (artist.getId() == 0) {
            artist.setId(nextId++);
            artists.add(artist);
        } else {
            artists.removeIf(a -> a.getId() == artist.getId());
            artists.add(artist);
        }
        return artist;
    }

    @Override
    public void delete(Artist artist) {
        artists.removeIf(a -> a.getId() == artist.getId());
    }

    @Override
    public List<Artist> findAllFollowedBy(int userId) {
        // This would require iterating through your follows data if you keep it in memory
        // For now, let's return an empty list
        return new ArrayList<>();
    }
}