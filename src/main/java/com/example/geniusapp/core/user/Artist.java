package com.example.geniusapp.core.user;

import com.example.geniusapp.content.Album;
import com.example.geniusapp.content.LyricEdit;
import com.example.geniusapp.content.Song;
import com.example.geniusapp.interaction.Follow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Date;

public class Artist extends Account {
    private String bio;
    private boolean verificationStatus;
    private List<Song> songs;
    private List<Album> albums;
    private List<User> followers;
    private List<Follow> followedBy;
    private Date memberSince;



    public Artist(int id, String username, String email, String password, String bio) {
        super(id, username, email, password);
        this.bio = bio;
        this.verificationStatus = false;
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.followedBy = new ArrayList<>(); // Initialize followedBy here
        this.memberSince = memberSince;

    }

    public void createSong(String title, String lyrics, String genre, List<String> tags, Date releaseDate) {
        Song song = new Song(getNextSongId(), title, lyrics, genre, tags, this, null, releaseDate);
        this.songs.add(song);
        System.out.println("Song created: " + title);
    }

    public void createAlbum(String title, String coverArt, Date releaseDate, String genre) {
        Album album = new Album(getNextAlbumId(), title, coverArt, releaseDate, this, genre);
        this.albums.add(album);
        System.out.println("Album created: " + title);
    }

    public void approveLyricEdit(LyricEdit lyricEdit) {
        if (lyricEdit.getSong().getArtists().contains(this) && lyricEdit.getStatus().equals("pending")) {
            lyricEdit.approve();
            lyricEdit.getSong().updateLyrics(lyricEdit.getSuggestedLyrics());
            System.out.println("Lyric edit approved for song: " + lyricEdit.getSong().getTitle());
        } else {
            System.out.println("Cannot approve this lyric edit.");
        }
    }

    public void rejectLyricEdit(LyricEdit lyricEdit) {
        if (lyricEdit.getSong().getArtists().contains(this) && lyricEdit.getStatus().equals("pending")) {
            lyricEdit.reject();
            System.out.println("Lyric edit rejected for song: " + lyricEdit.getSong().getTitle());
        } else {
            System.out.println("Cannot reject this lyric edit.");
        }
    }

    public void updateLyrics(Song song, String newLyrics) {
        if (this.songs.contains(song)) {
            song.updateLyrics(newLyrics);
            System.out.println("Lyrics updated for song: " + song.getTitle());
        } else {
            System.out.println("You are not the artist of this song.");
        }
    }

    public Date getMemberSince() { // Ensure this method exists and is public
        return memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }

    public void addFollower(User user) {
        if (!followers.contains(user)) {
            followers.add(user);
        }
    }

    public void removeFollower(User user) {
        followers.remove(user);
    }

    @Override
    public boolean login(String password) {
        return getPassword().equals(password);
    }

    @Override
    public void logout() {
        System.out.println(getUsername() + " has logged out.");
    }

    public String getBio() {
        return bio;
    }

    public boolean isVerificationStatus() {
        return verificationStatus;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setVerificationStatus(boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    private int getNextSongId() {
        // In a real application, this would be handled by a database or unique ID generator
        return (int) (Math.random() * 1000);
    }

    private int getNextAlbumId() {
        // In a real application, this would be handled by a database or unique ID generator
        return (int) (Math.random() * 1000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Artist artist = (Artist) o;
        return verificationStatus == artist.verificationStatus && Objects.equals(bio, artist.bio) && Objects.equals(songs, artist.songs) && Objects.equals(albums, artist.albums) && Objects.equals(followers, artist.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bio, verificationStatus, songs, albums, followers);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", memberSince=" + getMemberSince() +
                ", bio='" + bio + '\'' +
                ", songs (count)=" + (songs != null ? songs.size() : 0) + // Print song count
                ", albums (count)=" + (albums != null ? albums.size() : 0) + // Print album count
                ", followedBy (count)=" + (followedBy != null ? followedBy.size() : 0) + // Print follower count
                '}';
    }
}