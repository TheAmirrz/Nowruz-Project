package com.example.geniusapp.dao;

import com.example.geniusapp.content.Album;
import com.example.geniusapp.content.Song;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.util.Platform;

import java.util.List;
import java.util.stream.Collectors;

public class SongDAO {
    private Platform platform = Platform.getInstance();

    public void save(Song song) {
        if (!platform.getAllSongs().contains(song)) {
            platform.getAllSongs().add(song);
            System.out.println("Song saved: " + song.getTitle());
        } else {
            System.out.println("Song already exists: " + song.getTitle());
        }
    }

    public void update(Song song) {
        for (int i = 0; i < platform.getAllSongs().size(); i++) {
            if (platform.getAllSongs().get(i).getId() == song.getId()) {
                platform.getAllSongs().set(i, song);
                System.out.println("Song updated: " + song.getTitle());
                return;
            }
        }
        System.out.println("Song not found for update: " + song.getTitle());
    }

    public void delete(Song song) {
        platform.getAllSongs().remove(song);
        System.out.println("Song deleted: " + song.getTitle());
    }

    public Song findById(int id) {
        return platform.getAllSongs().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public List<Song> findByArtist(Artist artist) {
        return platform.getAllSongs().stream().filter(s -> s.getArtists().contains(artist)).collect(Collectors.toList());
    }

    public List<Song> findByAlbum(Album album) {
        return platform.getAllSongs().stream().filter(s -> s.getAlbum() != null && s.getAlbum().equals(album)).collect(Collectors.toList());
    }
}