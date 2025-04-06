package com.example.geniusapp.core.user;

import com.example.geniusapp.interaction.Comment;
import com.example.geniusapp.content.Song;
import com.example.geniusapp.content.LyricEdit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Account {
    private List<Artist> followedArtists;
    private List<Song> viewHistory;
    private List<Comment> comments;

    public User(int id, String username, String email, String password) {
        super(id, username, email, password);
        this.followedArtists = new ArrayList<>();
        this.viewHistory = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void suggestLyricEdit(Song song, String originalLyrics, String suggestedLyrics) {
        LyricEdit lyricEdit = new LyricEdit(getNextLyricEditId(), originalLyrics, suggestedLyrics, this, song);
        System.out.println("Lyric edit suggested for song: " + song.getTitle());
        // In a real application, you would need to store this LyricEdit and notify the artist/admin
    }

    public void followArtist(Artist artist) {
        if (!followedArtists.contains(artist)) {
            followedArtists.add(artist);
            System.out.println("You are now following " + artist.getUsername());
            artist.addFollower(this);
        } else {
            System.out.println("You are already following " + artist.getUsername());
        }
    }

    public void unfollowArtist(Artist artist) {
        if (followedArtists.contains(artist)) {
            followedArtists.remove(artist);
            System.out.println("You have unfollowed " + artist.getUsername());
            artist.removeFollower(this);
        } else {
            System.out.println("You are not following " + artist.getUsername());
        }
    }

    public void comment(Song song, String content) {
        Comment comment = new Comment(getNextCommentId(), content, this, song);
        song.addComment(comment);
        this.comments.add(comment);
        System.out.println("Comment added to song: " + song.getTitle());
    }

    @Override
    public boolean login(String password) {
        return getPassword().equals(password);
    }

    @Override
    public void logout() {
        System.out.println(getUsername() + " has logged out.");
    }

    public List<Artist> getFollowedArtists() {
        return followedArtists;
    }

    public List<Song> getViewHistory() {
        return viewHistory;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setFollowedArtists(List<Artist> followedArtists) {
        this.followedArtists = followedArtists;
    }

    public void setViewHistory(List<Song> viewHistory) {
        this.viewHistory = viewHistory;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    private int getNextLyricEditId() {
        // In a real application, this would be handled by a database or unique ID generator
        return (int) (Math.random() * 1000);
    }

    private int getNextCommentId() {
        // In a real application, this would be handled by a database or unique ID generator
        return (int) (Math.random() * 1000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(followedArtists, user.followedArtists) && Objects.equals(viewHistory, user.viewHistory) && Objects.equals(comments, user.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), followedArtists, viewHistory, comments);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", followedArtists=" + followedArtists +
                ", viewHistory=" + viewHistory +
                ", comments=" + comments +
                ", creationDate=" + getCreationDate() +
                '}';
    }
}