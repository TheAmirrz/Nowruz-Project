package com.example.geniusapp.data.impl;

import com.example.geniusapp.core.user.Admin;
import com.example.geniusapp.data.AdminRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

public class DatabaseAdminRepository implements AdminRepository {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public DatabaseAdminRepository(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public Optional<Admin> findById(int id) {
        String sql = "SELECT id, username, email, password, creation_date FROM admins WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToAdmin(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        String sql = "SELECT id, username, email, password, creation_date FROM admins WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToAdmin(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT id, username, email, password, creation_date FROM admins";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                admins.add(mapResultSetToAdmin(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public Admin save(Admin admin) {
        if (admin.getId() == 0) {
            String sql = "INSERT INTO admins (username, email, password, creation_date) VALUES (?, ?, ?, ?) RETURNING id";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, admin.getUsername());
                pstmt.setString(2, admin.getEmail());
                pstmt.setString(3, admin.getPassword());
                pstmt.setTimestamp(4, new Timestamp(admin.getCreationDate().getTime()));
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    admin.setId(rs.getInt("id"));
                    return admin;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            String sql = "UPDATE admins SET username = ?, email = ?, password = ?, creation_date = ? WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, admin.getUsername());
                pstmt.setString(2, admin.getEmail());
                pstmt.setString(3, admin.getPassword());
                pstmt.setTimestamp(4, new Timestamp(admin.getCreationDate().getTime()));
                pstmt.setInt(5, admin.getId());
                pstmt.executeUpdate();
                return admin;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void delete(Admin admin) {
        String sql = "DELETE FROM admins WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, admin.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Admin mapResultSetToAdmin(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Date creationDate = new Date(rs.getTimestamp("creation_date").getTime());
        Admin admin = new Admin(id, username, email, password);
        admin.setCreationDate(creationDate);
        return admin;
    }
}