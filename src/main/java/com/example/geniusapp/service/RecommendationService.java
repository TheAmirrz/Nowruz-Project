package com.example.geniusapp.service;

import com.example.geniusapp.content.Song;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.core.user.User;
import com.example.geniusapp.util.Platform;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService {
    private Platform platform = Platform.getInstance();

    public List<Song> recommendSongs(User user) {
        List<Song> recommendations = new ArrayList<>();
        // Basic recommendation: suggest songs from artists the user follows
        for (Artist followedArtist : user.getFollowedArtists()) {
            recommendations.addAll(followedArtist.getSongs());
        }
        // In a real application, you would have more sophisticated algorithms
        System.out.println("Recommending songs for " + user.getUsername() + " (basic implementation).");
        return recommendations;
    }

    public List<Artist> recommendArtists(User user) {
        List<Artist> recommendations = new ArrayList<>();
        // Basic recommendation: suggest artists based on the genres of songs the user has viewed
        List<String> viewedGenres = new ArrayList<>();
        for (Song viewedSong : user.getViewHistory()) {
            if (!viewedGenres.contains(viewedSong.getGenre())) {
                viewedGenres.add(viewedSong.getGenre());
            }
        }
        for (Artist artist : platform.getAllArtists()) {
            for (Song song : artist.getSongs()) {
                if (viewedGenres.contains(song.getGenre()) && !user.getFollowedArtists().contains(artist) && !recommendations.contains(artist)) {
                    recommendations.add(artist);
                }
            }
        }
        System.out.println("Recommending artists for " + user.getUsername() + " (basic implementation).");
        return recommendations;
    }

    public void analyzeUserPreferences(User user) {
        // Basic analysis: count the number of songs viewed per genre
        java.util.Map<String, Integer> genreCounts = new java.util.HashMap<>();
        for (Song song : user.getViewHistory()) {
            genreCounts.put(song.getGenre(), genreCounts.getOrDefault(song.getGenre(), 0) + 1);
        }
        System.out.println("Analyzed preferences for " + user.getUsername() + ": " + genreCounts);
    }
}