package com.example.geniusapp.content;

import com.example.geniusapp.core.user.Artist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Album {
    private int id;
    private String title;
    private String coverArt;
    private List<Song> songs;
    private Date releaseDate;
    private Artist artist;
    private String genre;

    public Album(int id, String title, String coverArt, Date releaseDate, Artist artist, String genre) {
        this.id = id;
        this.title = title;
        this.coverArt = coverArt;
        this.songs = new ArrayList<>();
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.genre = genre;
    }

    public void addSong(Song song) {
        if (!this.songs.contains(song)) {
            this.songs.add(song);
            song.setAlbum(this);
        }
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
        song.setAlbum(null);
    }

    public double getTotalDuration() {
        // This would require storing or calculating the duration of each song
        System.out.println("Calculating total duration is not implemented in this basic version.");
        return 0.0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverArt() {
        return coverArt;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoverArt(String coverArt) {
        this.coverArt = coverArt;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id && title.equals(album.title) && Objects.equals(coverArt, album.coverArt) && Objects.equals(songs, album.songs) && Objects.equals(releaseDate, album.releaseDate) && artist.equals(album.artist) && Objects.equals(genre, album.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, coverArt, songs, releaseDate, artist, genre);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coverArt='" + coverArt + '\'' +
                ", songs=" + songs +
                ", releaseDate=" + releaseDate +
                ", artist=" + artist.getUsername() +
                ", genre='" + genre + '\'' +
                '}';
    }
}