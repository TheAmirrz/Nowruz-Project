// Interaction Classes
package com.example.geniusapp.interaction;

import com.example.geniusapp.content.Song;
import com.example.geniusapp.core.user.User;

import java.util.Date;
import java.util.Objects;

public class Comment {
    private int id;
    private String content;
    private User user;
    private Date timestamp;
    private int likes;
    private Song song;

    public Comment(int id, String content, User user, Song song) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.timestamp = new Date();
        this.likes = 0;
        this.song = song;
    }

    public void edit(String newContent) {
        this.content = newContent;
    }

    public void delete() {
        System.out.println("Comment deleted: " + this.content);
        // In a real application, you might need to handle database removal or marking as deleted
    }

    public void like() {
        this.likes++;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
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

    public Song getSong() {
        return song;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
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

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && likes == comment.likes && content.equals(comment.content) && user.equals(comment.user) && song.equals(comment.song);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, user, song, likes);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user.getUsername() +
                ", timestamp=" + timestamp +
                ", likes=" + likes +
                ", song=" + song.getTitle() +
                '}';
    }
}