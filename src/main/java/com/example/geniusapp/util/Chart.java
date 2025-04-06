// Helper/Utility Classes
package com.example.geniusapp.util;

import com.example.geniusapp.content.Song;

import java.util.ArrayList;
import java.util.List;

public class Chart {
    private String timeRange;
    private List<Song> songs;

    public Chart(String timeRange) {
        this.timeRange = timeRange;
        this.songs = new ArrayList<>();
    }

    public void updateRankings(List<Song> allSongs) {
        // Basic implementation: sorts all provided songs by view count
        this.songs = new ArrayList<>(allSongs);
        this.songs.sort((s1, s2) -> Integer.compare(s2.getViews(), s1.getViews()));
    }

    public void displayChart() {
        System.out.println("--- Top Songs (" + timeRange + ") ---");
        if (songs.isEmpty()) {
            System.out.println("No songs to display in the chart.");
        } else {
            for (int i = 0; i < Math.min(10, songs.size()); i++) {
                System.out.println((i + 1) + ". " + songs.get(i).getTitle() + " - Views: " + songs.get(i).getViews());
            }
        }
        System.out.println("----------------------------------");
    }
}