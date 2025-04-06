package com.example.geniusapp.ui;

import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.interaction.Comment;
import com.example.geniusapp.content.Song;

import java.util.List;

public class SongPage {
    private Song song;

    public SongPage(Song song) {
        this.song = song;
    }

    public void displayLyrics() {
        System.out.println("--- Lyrics for '" + song.getTitle() + "' ---");
        System.out.println(song.getLyrics());
        System.out.println("-------------------------------------------");
    }

    public void displayComments() {
        System.out.println("--- Comments on '" + song.getTitle() + "' ---");
        if (song.getComments().isEmpty()) {
            System.out.println("No comments yet.");
        } else {
            for (Comment comment : song.getComments()) {
                System.out.println(comment.getUser().getUsername() + ": " + comment.getContent() + " (Likes: " + comment.getLikes() + ")");
            }
        }
        System.out.println("--------------------------------------------");
    }

    public void showRelatedSongs() {
        System.out.println("--- Related Songs (Basic Implementation) ---");
        // This is a very basic implementation. A real system would have more sophisticated logic.
        if (song.getAlbum() != null) {
            for (Song albumSong : song.getAlbum().getSongs()) {
                if (!albumSong.equals(song)) {
                    System.out.println("- " + albumSong.getTitle() + " (from the same album)");
                }
            }
        }
        for (Artist artist : song.getArtists()) {
            for (Song artistSong : artist.getSongs()) {
                if (!artistSong.equals(song) && (song.getAlbum() == null || !artistSong.getAlbum().equals(song.getAlbum()))) {
                    System.out.println("- " + artistSong.getTitle() + " (by the same artist)");
                }
            }
        }
        System.out.println("---------------------------------------------");
    }
}