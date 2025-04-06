package com.example.geniusapp.interaction;

import com.example.geniusapp.core.user.User;

import java.util.Date;
import java.util.Objects;

public class Notification {
    private int id;
    private User user;
    private String content;
    private Date timestamp;
    private boolean isRead;
    private String type;
    private Object relatedEntity; // Can be Song, Album, etc.

    public Notification(int id, User user, String content, String type, Object relatedEntity) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.timestamp = new Date();
        this.isRead = false;
        this.type = type;
        this.relatedEntity = relatedEntity;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public void dismiss() {
        System.out.println("Notification dismissed: " + this.content);
        // In a real application, you might handle permanent removal
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public String getType() {
        return type;
    }

    public Object getRelatedEntity() {
        return relatedEntity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRelatedEntity(Object relatedEntity) {
        this.relatedEntity = relatedEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification notification = (Notification) o;
        return id == notification.id && isRead == notification.isRead && user.equals(notification.user) && content.equals(notification.content) && type.equals(notification.type) && Objects.equals(relatedEntity, notification.relatedEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, content, isRead, type, relatedEntity);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", isRead=" + isRead +
                ", type='" + type + '\'' +
                ", relatedEntity=" + relatedEntity +
                '}';
    }
}