// UI Classes
package com.example.geniusapp.ui;

import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.core.user.User;
import com.example.geniusapp.interaction.Comment;
import com.example.geniusapp.content.Song;

import java.util.List;

public class UserPage {
    private User user;

    public UserPage(User user) {
        this.user = user;
    }

    public void displayProfile() {
        System.out.println("--- User Profile ---");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Member Since: " + user.getCreationDate());
        System.out.println("----------------------");
    }

    public void showFollowedArtists() {
        System.out.println("--- Followed Artists ---");
        if (user.getFollowedArtists().isEmpty()) {
            System.out.println("You are not following any artists.");
        } else {
            for (Artist artist : user.getFollowedArtists()) {
                System.out.println("- " + artist.getUsername());
            }
        }
        System.out.println("-------------------------");
    }

    public void showCommentHistory() {
        System.out.println("--- Comment History ---");
        if (user.getComments().isEmpty()) {
            System.out.println("You haven't commented on any songs yet.");
        } else {
            for (Comment comment : user.getComments()) {
                System.out.println("- On '" + comment.getSong().getTitle() + "': " + comment.getContent());
            }
        }
        System.out.println("-----------------------");
    }
}