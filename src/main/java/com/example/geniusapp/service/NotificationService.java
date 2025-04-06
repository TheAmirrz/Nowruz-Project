package com.example.geniusapp.service;

import com.example.geniusapp.core.user.User;
import com.example.geniusapp.interaction.Notification;
import com.example.geniusapp.util.Platform;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private Platform platform = Platform.getInstance();
    private int nextNotificationId = 1;

    public void notifyUser(User user, String content, String type, Object relatedEntity) {
        Notification notification = new Notification(nextNotificationId++, user, content, type, relatedEntity);
        // In a real application, you would store this in a database associated with the user
        System.out.println("Notification sent to " + user.getUsername() + ": " + content);
    }

    public List<Notification> getUnreadNotifications(User user) {
        List<Notification> unread = new ArrayList<>();
        // In a real application, you would fetch this from a database
        System.out.println("Fetching unread notifications for " + user.getUsername() + " is not fully implemented.");
        return unread;
    }

    public void markAllAsRead(User user) {
        // In a real application, you would update the status in a database
        System.out.println("Marking all notifications as read for " + user.getUsername() + " is not fully implemented.");
    }
}