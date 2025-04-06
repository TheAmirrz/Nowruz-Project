package com.example.geniusapp.content;

import com.example.geniusapp.core.user.User;

import java.util.Date;
import java.util.Objects;

public class LyricEdit {
    private int id;
    private String originalLyrics;
    private String suggestedLyrics;
    private User user;
    private Song song;
    private String status; // pending/approved/rejected
    private Date timestamp;

    public LyricEdit(int id, String originalLyrics, String suggestedLyrics, User user, Song song) {
        this.id = id;
        this.originalLyrics = originalLyrics;
        this.suggestedLyrics = suggestedLyrics;
        this.user = user;
        this.song = song;
        this.status = "pending";
        this.timestamp = new Date();
    }

    public void approve() {
        this.status = "approved";
    }

    public void reject() {
        this.status = "rejected";
    }

    public String getChangeDescription() {
        // Basic implementation: showing the difference
        return "Original: " + originalLyrics + "\nSuggested: " + suggestedLyrics;
    }

    public int getId() {
        return id;
    }

    public String getOriginalLyrics() {
        return originalLyrics;
    }

    public String getSuggestedLyrics() {
        return suggestedLyrics;
    }

    public User getUser() {
        return user;
    }

    public Song getSong() {
        return song;
    }

    public String getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginalLyrics(String originalLyrics) {
        this.originalLyrics = originalLyrics;
    }

    public void setSuggestedLyrics(String suggestedLyrics) {
        this.suggestedLyrics = suggestedLyrics;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LyricEdit lyricEdit = (LyricEdit) o;
        return id == lyricEdit.id && originalLyrics.equals(lyricEdit.originalLyrics) && suggestedLyrics.equals(lyricEdit.suggestedLyrics) && user.equals(lyricEdit.user) && song.equals(lyricEdit.song) && status.equals(lyricEdit.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalLyrics, suggestedLyrics, user, song, status);
    }

    @Override
    public String toString() {
        return "LyricEdit{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", song=" + song.getTitle() +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}