package com.example.geniusapp.core.user;

import com.example.geniusapp.interaction.Comment;

public class Admin extends Account {
    public Admin(int id, String username, String email, String password) {
        super(id, username, email, password);
    }

    public void approveArtistRegistration(Artist artist) {
        artist.setVerificationStatus(true);
        System.out.println("Artist registration approved for: " + artist.getUsername());
    }

    public void moderateContent(String content) {
        System.out.println("Content moderated: " + content);
        // In a real application, you would implement content filtering or review processes
    }

    public void removeComment(Comment comment) {
        comment.getSong().getComments().remove(comment);
        comment.getUser().getComments().remove(comment);
        System.out.println("Comment removed: " + comment.getContent());
    }

    public void banUser(User user) {
        System.out.println("User banned: " + user.getUsername());
        // In a real application, you would implement user blocking mechanisms
    }

    @Override
    public boolean login(String password) {
        return getPassword().equals(password);
    }

    @Override
    public void logout() {
        System.out.println(getUsername() + " has logged out.");
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", creationDate=" + getCreationDate() +
                '}';
    }
}