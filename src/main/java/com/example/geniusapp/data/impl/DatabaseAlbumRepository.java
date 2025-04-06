package com.example.geniusapp.data.impl;

import com.example.geniusapp.content.Album;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.data.AlbumRepository;
import com.example.geniusapp.data.ArtistRepository; // Import ArtistRepository
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

public class DatabaseAlbumRepository implements AlbumRepository {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final ArtistRepository artistRepository; // Add ArtistRepository dependency

    public DatabaseAlbumRepository(String dbUrl, String dbUser, String dbPassword, ArtistRepository artistRepository) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.artistRepository = artistRepository;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public Optional<Album> findById(int id) {
        String sql = "SELECT id, title, cover_art, artist_id, release_date, genre FROM albums WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToAlbum(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Album> findByTitleAndArtistUsername(String title, String artistUsername) {
        String sql = "SELECT a.id, a.title, a.cover_art, a.artist_id, a.release_date, a.genre FROM albums a " +
                "JOIN users u ON a.artist_id = u.id WHERE a.title = ? AND u.username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, artistUsername);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToAlbum(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Album> findAll() {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT id, title, cover_art, artist_id, release_date, genre FROM albums";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                albums.add(mapResultSetToAlbum(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    @Override
    public Album save(Album album) {
        if (album.getId() == 0) {
            String sql = "INSERT INTO albums (title, cover_art, artist_id, release_date, genre) VALUES (?, ?, ?, ?, ?) RETURNING id";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, album.getTitle());
                pstmt.setString(2, album.getCoverArt());
                pstmt.setInt(3, album.getArtist().getId());
                pstmt.setTimestamp(4, new Timestamp(album.getReleaseDate().getTime()));
                pstmt.setString(5, album.getGenre());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    album.setId(rs.getInt("id"));
                    return album;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            String sql = "UPDATE albums SET title = ?, cover_art = ?, artist_id = ?, release_date = ?, genre = ? WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, album.getTitle());
                pstmt.setString(2, album.getCoverArt());
                pstmt.setInt(3, album.getArtist().getId());
                pstmt.setTimestamp(4, new Timestamp(album.getReleaseDate().getTime()));
                pstmt.setString(5, album.getGenre());
                pstmt.setInt(6, album.getId());
                pstmt.executeUpdate();
                return album;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void delete(Album album) {
        String sql = "DELETE FROM albums WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, album.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Album> findByArtistUsername(String artistUsername) {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT a.id, a.title, a.cover_art, a.artist_id, a.release_date, a.genre FROM albums a " +
                "JOIN users u ON a.artist_id = u.id WHERE u.username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, artistUsername);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                albums.add(mapResultSetToAlbum(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    private Album mapResultSetToAlbum(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String coverArt = rs.getString("cover_art");
        int artistId = rs.getInt("artist_id");
        Date releaseDate = new Date(rs.getTimestamp("release_date").getTime());
        String genre = rs.getString("genre");
        Optional<Artist> artistOptional = artistRepository.findById(artistId);
        return artistOptional.map(artist -> new Album(id, title, coverArt, releaseDate, artist, genre))
                .orElse(null); // Handle the case where the artist is not found
    }
    @Override
    public List<Album> findByTitleContaining(String query) {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT id, title, cover_art, release_date, artist_username, genre FROM albums WHERE LOWER(title) LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + query.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String coverArt = rs.getString("cover_art");
                Date releaseDate = rs.getDate("release_date");
                String artistUsername = rs.getString("artist_username");
                String genre = rs.getString("genre");

                Optional<Artist> artistOptional = artistRepository.findByUsername(artistUsername);
                Artist artist = artistOptional.orElse(null);

                Album album = new Album(id, title, coverArt, releaseDate, artist, genre);
                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }
}