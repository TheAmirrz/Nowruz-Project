package com.example.geniusapp.data.impl;

import com.example.geniusapp.content.Album;
import com.example.geniusapp.data.AlbumRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryAlbumRepository implements AlbumRepository {
    private final List<Album> albums = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Optional<Album> findById(int id) {
        return albums.stream().filter(album -> album.getId() == id).findFirst();
    }

    @Override
    public Optional<Album> findByTitleAndArtistUsername(String title, String artistUsername) {
        return albums.stream()
                .filter(album -> album.getTitle().equals(title) && album.getArtist().getUsername().equals(artistUsername))
                .findFirst();
    }

    @Override
    public List<Album> findAll() {
        return new ArrayList<>(albums);
    }

    @Override
    public Album save(Album album) {
        if (album.getId() == 0) {
            album.setId(nextId++);
            albums.add(album);
        } else {
            albums.removeIf(a -> a.getId() == album.getId());
            albums.add(album);
        }
        return album;
    }

    @Override
    public void delete(Album album) {
        albums.removeIf(a -> a.getId() == album.getId());
    }

    @Override
    public List<Album> findByArtistUsername(String artistUsername) {
        return albums.stream()
                .filter(album -> album.getArtist().getUsername().equals(artistUsername))
                .collect(java.util.stream.Collectors.toList());
    }
    @Override
    public List<Album> findByTitleContaining(String query) {
        return albums.stream()
                .filter(album -> album.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}