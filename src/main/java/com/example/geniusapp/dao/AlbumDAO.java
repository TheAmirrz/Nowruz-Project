package com.example.geniusapp.dao;

import com.example.geniusapp.content.Album;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.util.Platform;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumDAO {
    private Platform platform = Platform.getInstance();

    public void save(Album album) {
        if (!platform.getAllAlbums().contains(album)) {
            platform.getAllAlbums().add(album);
            System.out.println("Album saved: " + album.getTitle());
        } else {
            System.out.println("Album already exists: " + album.getTitle());
        }
    }

    public void update(Album album) {
        for (int i = 0; i < platform.getAllAlbums().size(); i++) {
            if (platform.getAllAlbums().get(i).getId() == album.getId()) {
                platform.getAllAlbums().set(i, album);
                System.out.println("Album updated: " + album.getTitle());
                return;
            }
        }
        System.out.println("Album not found for update: " + album.getTitle());
    }

    public void delete(Album album) {
        platform.getAllAlbums().remove(album);
        System.out.println("Album deleted: " + album.getTitle());
    }

    public Album findById(int id) {
        return platform.getAllAlbums().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public List<Album> findByArtist(Artist artist) {
        return platform.getAllAlbums().stream().filter(a -> a.getArtist().equals(artist)).collect(Collectors.toList());
    }
}