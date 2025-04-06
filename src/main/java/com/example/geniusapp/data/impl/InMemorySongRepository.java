package com.example.geniusapp.data.impl;

import com.example.geniusapp.content.Song;
import com.example.geniusapp.data.SongRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemorySongRepository implements SongRepository {
    private final List<Song> songs = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Optional<Song> findById(int id) {
        return songs.stream().filter(song -> song.getId() == id).findFirst();
    }

    @Override
    public Optional<Song> findByTitleAndArtistUsername(String title, String artistUsername) {
        return songs.stream()
                .filter(song -> song.getTitle().equals(title) &&
                        song.getArtists() != null &&
                        song.getArtists().stream()
                                .anyMatch(artist -> artist.getUsername().equals(artistUsername)))
                .findFirst();
    }

    @Override
    public List<Song> findByArtistUsername(String artistUsername) {
        return songs.stream()
                .filter(song -> song.getArtists() != null &&
                        song.getArtists().stream()
                                .anyMatch(artist -> artist.getUsername().equals(artistUsername)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songs);
    }

    @Override
    public Song save(Song song) {
        if (song.getId() == 0) {
            song.setId(nextId++);
            songs.add(song);
        } else {
            songs.removeIf(s -> s.getId() == song.getId());
            songs.add(song);
        }
        return song;
    }

    @Override
    public void delete(Song song) {
        songs.removeIf(s -> s.getId() == song.getId());
    }

    // Add this method:
    @Override
    public List<Song> findByTitleContaining(String query) {
        return songs.stream()
                .filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}