package com.example.geniusapp.util;

import com.example.geniusapp.core.user.User;

import java.util.Date;
import java.util.Objects;

public class LyricAnalysis {
    private int id;
    private String lyricSegment;
    private String analysis;
    private User user;
    private Date timestamp;
    private int likes;

    public LyricAnalysis(int id, String lyricSegment, String analysis, User user) {
        this.id = id;
        this.lyricSegment = lyricSegment;
        this.analysis = analysis;
        this.user = user;
        this.timestamp = new Date();
        this.likes = 0;
    }

    public void edit(String newAnalysis) {
        this.analysis = newAnalysis;
    }

    public void vote() {
        this.likes++;
    }

    public void displayAnalysis() {
        System.out.println("--- Lyric Analysis ---");
        System.out.println("Segment: " + lyricSegment);
        System.out.println("Analysis by " + user.getUsername() + ": " + analysis);
        System.out.println("Likes: " + likes);
        System.out.println("----------------------");
    }

    public int getId() {
        return id;
    }

    public String getLyricSegment() {
        return lyricSegment;
    }

    public String getAnalysis() {
        return analysis;
    }

    public User getUser() {
        return user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLyricSegment(String lyricSegment) {
        this.lyricSegment = lyricSegment;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LyricAnalysis that = (LyricAnalysis) o;
        return id == that.id && likes == that.likes && lyricSegment.equals(that.lyricSegment) && analysis.equals(that.analysis) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lyricSegment, analysis, user, likes);
    }

    @Override
    public String toString() {
        return "LyricAnalysis{" +
                "id=" + id +
                ", lyricSegment='" + lyricSegment + '\'' +
                ", analysis='" + analysis + '\'' +
                ", user=" + user.getUsername() +
                ", timestamp=" + timestamp +
                ", likes=" + likes +
                '}';
    }
}