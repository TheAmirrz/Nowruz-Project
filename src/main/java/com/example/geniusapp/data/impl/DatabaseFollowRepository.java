package com.example.geniusapp.data.impl;

import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.core.user.User;
import com.example.geniusapp.data.ArtistRepository; // Import ArtistRepository
import com.example.geniusapp.data.FollowRepository;
import com.example.geniusapp.data.UserRepository; // Import UserRepository
import com.example.geniusapp.interaction.Follow;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Date;

public class DatabaseFollowRepository implements FollowRepository {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final UserRepository userRepository; // Add UserRepository dependency
    private final ArtistRepository artistRepository; // Add ArtistRepository dependency

    public DatabaseFollowRepository(String dbUrl, String dbUser, String dbPassword, UserRepository userRepository, ArtistRepository artistRepository) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public Optional<Follow> findByUserIdAndArtistId(int userId, int artistId) {
        String sql = "SELECT user_id, artist_id, timestamp FROM follows WHERE user_id = ? AND artist_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, artistId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToFollow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Follow> findAll() {
        List<Follow> follows = new ArrayList<>();
        String sql = "SELECT user_id, artist_id, timestamp FROM follows";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                follows.add(mapResultSetToFollow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return follows;
    }

    @Override
    public Follow save(Follow follow) {
        String sql = "INSERT INTO follows (user_id, artist_id, timestamp) VALUES (?, ?, ?) ON CONFLICT (user_id, artist_id) DO NOTHING";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, follow.getUser().getId());
            pstmt.setInt(2, follow.getArtist().getId());
            pstmt.setTimestamp(3, new Timestamp(follow.getTimestamp().getTime()));
            pstmt.executeUpdate();
            return follow;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteByUserIdAndArtistId(int userId, int artistId) {
        String sql = "DELETE FROM follows WHERE user_id = ? AND artist_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, artistId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Artist> findAllFollowedBy(int userId) {
        List<Artist> followedArtists = new ArrayList<>();
        String sql = "SELECT u.* FROM users u JOIN follows f ON u.id = f.artist_id WHERE f.user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                followedArtists.add(mapResultSetToArtist(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return followedArtists;
    }

    private Follow mapResultSetToFollow(ResultSet rs) throws SQLException {
        int userId = rs.getInt("user_id");
        int artistId = rs.getInt("artist_id");
        Date timestamp = new Date(rs.getTimestamp("timestamp").getTime());
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Artist> artistOptional = artistRepository.findById(artistId);
        if (userOptional.isPresent() && artistOptional.isPresent()) {
            return new Follow(userOptional.get(), artistOptional.get());
        }
        return null; // Handle the case where user or artist is not found
    }

    private Artist mapResultSetToArtist(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Date memberSince = new Date(rs.getTimestamp("member_since").getTime());
        String bio = rs.getString("bio");
        Artist artist = new Artist(0,username, email, password, bio);
        artist.setId(id); // Set the ID retrieved from the database
        return artist;
    }
    @Override
    public Optional<Follow> findByUserIdAndArtistUsername(int userId, String artistUsername) {
        String sql = "SELECT f.user_id, f.artist_id, f.timestamp FROM follows f " +
                "JOIN users u ON f.artist_id = u.id WHERE f.user_id = ? AND u.username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, artistUsername);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToFollow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}