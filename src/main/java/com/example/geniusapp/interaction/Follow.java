package com.example.geniusapp.interaction;

import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.core.user.User;

import java.util.Date;
import java.util.Objects;

public class Follow {
    private User user;
    private Artist artist;
    private Date timestamp;

    public Follow(User user, Artist artist) {
        this.user = user;
        this.artist = artist;
        this.timestamp = new Date();
        sendFollowNotification(); // Immediately notify the artist upon being followed
    }

    public void sendFollowNotification() {
        System.out.println(user.getUsername() + " has started following " + artist.getUsername());
        // In a real application, you would use a NotificationService to handle this
    }

    public User getUser() {
        return user;
    }

    public Artist getArtist() {
        return artist;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return user.equals(follow.user) && artist.equals(follow.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, artist);
    }

    @Override
    public String toString() {
        return "Follow{" +
                "user=" + user.getUsername() +
                ", artist=" + artist.getUsername() +
                ", timestamp=" + timestamp +
                '}';
    }
}