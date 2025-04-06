package com.example.geniusapp.ui;

import com.example.geniusapp.content.Album;
import com.example.geniusapp.content.Song;
import com.example.geniusapp.core.user.Artist;

import java.util.List;

public class AlbumPage {
    private Album album;

    public AlbumPage(Album album) {
        this.album = album;
    }

    public void displayAlbumInfo() {
        System.out.println("--- Album Information ---");
        System.out.println("Title: " + album.getTitle());
        System.out.println("Artist: " + album.getArtist().getUsername());
        System.out.println("Release Date: " + album.getReleaseDate());
        System.out.println("Genre: " + album.getGenre());
        System.out.println("-------------------------");
    }

    public void listSongs() {
        System.out.println("--- Songs in '" + album.getTitle() + "' ---");
        if (album.getSongs().isEmpty()) {
            System.out.println("No songs in this album.");
        } else {
            for (Song song : album.getSongs()) {
                System.out.println("- " + song.getTitle());
            }
        }
        System.out.println("----------------------------------");
    }

    public void showArtist() {
        Artist artist = album.getArtist();
        System.out.println("--- Artist Information ---");
        System.out.println("Name: " + artist.getUsername());
        System.out.println("Bio: " + artist.getBio());
        System.out.println("--------------------------");
        // In a real application, you might navigate to the ArtistPage
    }
}