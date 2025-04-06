package com.example.geniusapp.data.impl;

import com.example.geniusapp.content.Album;
import com.example.geniusapp.content.Song;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.data.AlbumRepository;
import com.example.geniusapp.data.ArtistRepository;
import com.example.geniusapp.data.SongRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseSongRepository implements SongRepository {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final ArtistRepository artistRepository; // Assuming you have this
    private final AlbumRepository albumRepository;   // Assuming you have this

    public DatabaseSongRepository(String dbUrl, String dbUser, String dbPassword, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public Optional<Song> findById(int id) {
        // Implement database logic
        return Optional.empty();
    }

    @Override
    public Optional<Song> findByTitleAndArtistUsername(String title, String artistUsername) {
        // Implement database logic
        return Optional.empty();
    }

    @Override
    public List<Song> findAll() {
        // Implement database logic
        return new ArrayList<>();
    }

    @Override
    public Song save(Song song) {
        // Implement database logic
        return null;
    }

    @Override
    public void delete(Song song) {
        // Implement database logic
    }

    @Override
    public List<Song> findByArtistUsername(String artistUsername) {
        // Implement database logic
        return new ArrayList<>();
    }
    @Override
    public List<Song> findByTitleContaining(String query) {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT id, title, lyrics, genre, tags, artist_username, album_title, release_date FROM songs WHERE LOWER(title) LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + query.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String lyrics = rs.getString("lyrics");
                String genre = rs.getString("genre");
                // ... (retrieve other fields) ...
                String artistUsername = rs.getString("artist_username");
                String albumTitle = rs.getString("album_title");
                Date releaseDate = rs.getDate("release_date");

                Optional<Artist> artistOptional = artistRepository.findByUsername(artistUsername);
                Artist artist = artistOptional.orElse(null);
                Album album = null;
                if (albumTitle != null) {
                    Optional<Album> albumOptional = albumRepository.findByTitleAndArtistUsername(albumTitle, artistUsername);
                    album = albumOptional.orElse(null);
                }

                // Assuming tags are stored as a comma-separated string
                List<String> tags = new ArrayList<>();
                String tagsString = rs.getString("tags");
                if (tagsString != null) {
                    tags.addAll(List.of(tagsString.split(",")));
                }

                Song song = new Song(id, title, lyrics, genre, tags, artist, album, releaseDate);
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }
}