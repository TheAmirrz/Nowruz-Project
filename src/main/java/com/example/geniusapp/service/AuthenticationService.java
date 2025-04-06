// Service Classes
package com.example.geniusapp.service;

import com.example.geniusapp.core.user.*;
import com.example.geniusapp.interaction.Notification;
import com.example.geniusapp.util.Platform;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    private Platform platform = Platform.getInstance();
    private int nextAccountId = 6; // Start after the initial seed data

    public Account register(String username, String email, String password, String role) {
        if (platform.getAllAccounts().stream().anyMatch(account -> account.getUsername().equals(username))) {
            System.out.println("Username already exists.");
            return null;
        }
        Account newAccount = null;
        int newId = nextAccountId++;
        switch (role.toLowerCase()) {
            case "user":
                newAccount = new User(newId, username, email, password);
                break;
            case "artist":
                newAccount = new Artist(newId, username, email, password, ""); // Basic artist with empty bio
                break;
            case "admin":
                newAccount = new Admin(newId, username, email, password);
                break;
            default:
                System.out.println("Invalid role specified.");
                return null;
        }
        if (newAccount != null) {
            platform.getAllAccounts().add(newAccount);
            if (newAccount instanceof Artist) {
                platform.getAllArtists().add((Artist) newAccount);
            }
            System.out.println("Account created successfully.");
            return newAccount;
        }
        return null;
    }

    public Account login(String username, String password) {
        for (Account account : platform.getAllAccounts()) {
            if (account.getUsername().equals(username) && account.login(password)) {
                System.out.println(username + " logged in successfully.");
                return account;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public void logout(Account account) {
        if (account != null) {
            account.logout();
        }
    }

    public void resetPassword(String email) {
        for (Account account : platform.getAllAccounts()) {
            if (account.getEmail().equals(email)) {
                String newPassword = generateRandomPassword();
                account.setPassword(newPassword);
                System.out.println("Password reset successfully. New password sent to " + email);
                // In a real application, you would send an email with the new password
                return;
            }
        }
        System.out.println("No account found with this email.");
    }

    private String generateRandomPassword() {
        // Basic password generator (for demonstration purposes only)
        return "tempPassword123";
    }
}