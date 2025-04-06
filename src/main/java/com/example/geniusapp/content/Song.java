// Music Content Classes
package com.example.geniusapp.content;

import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.interaction.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Song {
    private int id;
    private String title;
    private String lyrics;
    private String genre;
    private List<String> tags;
    private int views;
    private List<Comment> comments;
    private List<Artist> artists;
    private Album album;
    private Date releaseDate;

    public Song(int id, String title, String lyrics, String genre, List<String> tags, Artist artist, Album album, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.lyrics = lyrics;
        this.genre = genre;
        this.tags = tags;
        this.views = 0;
        this.comments = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.artists.add(artist); // Assuming at least one artist
        this.album = album;
        this.releaseDate = releaseDate;
    }

    public void incrementViews() {
        this.views++;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public void updateLyrics(String newLyrics) {
        this.lyrics = newLyrics;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getViews() {
        return views;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Album getAlbum() {
        return album;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id && views == song.views && title.equals(song.title) && lyrics.equals(song.lyrics) && genre.equals(song.genre) && Objects.equals(tags, song.tags) && Objects.equals(artists, song.artists) && Objects.equals(album, song.album) && Objects.equals(releaseDate, song.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, lyrics, genre, tags, views, artists, album, releaseDate);
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + getTitle() + '\'' +
                ", lyrics='" + (lyrics.length() > 50 ? lyrics.substring(0, 50) + "..." : lyrics) + '\'' + // Limit lyrics for brevity
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", artists=" + (artists != null ? artists.stream().map(Artist::getUsername).collect(java.util.stream.Collectors.toList()) : "null") +                ", album=" + (album != null ? album.getTitle() : "null") +
                ", views=" + views +
                ", tags=" + tags +
                ", comments (count)=" + (comments != null ? comments.size() : 0) + // Print comment count
                '}';
    }
}