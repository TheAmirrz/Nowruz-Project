package com.example.geniusapp.ui;

import com.example.geniusapp.content.Album;
import com.example.geniusapp.content.Song;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.util.Platform;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private Platform platform;

    public SearchEngine(Platform platform) {
        this.platform = platform;
    }

    public List<Artist> searchByArtist(String query) {
        List<Artist> results = new ArrayList<>();
        for (Artist artist : platform.getAllArtists()) {
            if (artist.getUsername().toLowerCase().contains(query.toLowerCase())) {
                results.add(artist);
            }
        }
        return results;
    }

    public List<Song> searchBySong(String query) {
        List<Song> results = new ArrayList<>();
        for (Song song : platform.getAllSongs()) {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(song);
            }
        }
        return results;
    }

    public List<Album> searchByAlbum(String query) {
        List<Album> results = new ArrayList<>();
        for (Album album : platform.getAllAlbums()) {
            if (album.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(album);
            }
        }
        return results;
    }

    public List<Song> searchByLyrics(String query) {
        List<Song> results = new ArrayList<>();
        for (Song song : platform.getAllSongs()) {
            if (song.getLyrics().toLowerCase().contains(query.toLowerCase())) {
                results.add(song);
            }
        }
        return results;
    }
}