package com.example.geniusapp.data.impl;

import com.example.geniusapp.core.user.User;
import com.example.geniusapp.content.Song;
import com.example.geniusapp.data.CommentRepository;
import com.example.geniusapp.data.SongRepository; // Import SongRepository
import com.example.geniusapp.data.UserRepository; // Import UserRepository
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import com.example.geniusapp.interaction.Comment;

public class DatabaseCommentRepository implements CommentRepository {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final UserRepository userRepository; // Add UserRepository dependency
    private final SongRepository songRepository; // Add SongRepository dependency

    public DatabaseCommentRepository(String dbUrl, String dbUser, String dbPassword, UserRepository userRepository, SongRepository songRepository) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public Optional<Comment> findById(int id) {
        String sql = "SELECT id, user_id, song_id, content, timestamp, likes FROM comments WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToComment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findBySongId(int songId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT id, user_id, song_id, content, timestamp, likes FROM comments WHERE song_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, songId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                comments.add(mapResultSetToComment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Optional<Comment> findByContentAndSongTitleAndUsername(String content, String songTitle, String username) {
        String sql = "SELECT c.id, c.user_id, c.song_id, c.content, c.timestamp, c.likes " +
                "FROM comments c " +
                "JOIN users u ON c.user_id = u.id " +
                "JOIN songs s ON c.song_id = s.id " +
                "WHERE c.content = ? AND s.title = ? AND u.username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, content);
            pstmt.setString(2, songTitle);
            pstmt.setString(3, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToComment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT id, user_id, song_id, content, timestamp, likes FROM comments";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                comments.add(mapResultSetToComment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            String sql = "INSERT INTO comments (user_id, song_id, content, timestamp, likes) VALUES (?, ?, ?, ?, ?) RETURNING id";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, comment.getUser().getId());
                pstmt.setInt(2, comment.getSong().getId());
                pstmt.setString(3, comment.getContent());
                pstmt.setTimestamp(4, new Timestamp(comment.getTimestamp().getTime()));
                pstmt.setInt(5, comment.getLikes());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    comment.setId(rs.getInt("id"));
                    return comment;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            String sql = "UPDATE comments SET user_id = ?, song_id = ?, content = ?, timestamp = ?, likes = ? WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, comment.getUser().getId());
                pstmt.setInt(2, comment.getSong().getId());
                pstmt.setString(3, comment.getContent());
                pstmt.setTimestamp(4, new Timestamp(comment.getTimestamp().getTime()));
                pstmt.setInt(5, comment.getLikes());
                pstmt.setInt(6, comment.getId());
                pstmt.executeUpdate();
                return comment;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void delete(Comment comment) {
        String sql = "DELETE FROM comments WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, comment.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Comment mapResultSetToComment(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int userId = rs.getInt("user_id");
        int songId = rs.getInt("song_id");
        String content = rs.getString("content");
        Date timestamp = new Date(rs.getTimestamp("timestamp").getTime());
        int likes = rs.getInt("likes");
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Song> songOptional = songRepository.findById(songId);
        if (userOptional.isPresent() && songOptional.isPresent()) {
            return new Comment(id, content, userOptional.get(), songOptional.get());
        }
        return null; // Handle the case where user or song is not found
    }
}