package com.example.geniusapp.data.impl;

import com.example.geniusapp.interaction.Comment;
import com.example.geniusapp.data.CommentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryCommentRepository implements CommentRepository {
    private final List<Comment> comments = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Optional<Comment> findById(int id) {
        return comments.stream().filter(comment -> comment.getId() == id).findFirst();
    }

    @Override
    public List<Comment> findBySongId(int songId) {
        return comments.stream().filter(comment -> comment.getSong().getId() == songId).collect(Collectors.toList());
    }

    @Override
    public Optional<Comment> findByContentAndSongTitleAndUsername(String content, String songTitle, String username) {
        return comments.stream()
                .filter(comment -> comment.getContent().equals(content) &&
                        comment.getSong().getTitle().equals(songTitle) &&
                        comment.getUser().getUsername().equals(username))
                .findFirst();
    }

    @Override
    public List<Comment> findAll() {
        return new ArrayList<>(comments);
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            comment.setId(nextId++);
            comments.add(comment);
        } else {
            comments.removeIf(c -> c.getId() == comment.getId());
            comments.add(comment);
        }
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        comments.removeIf(c -> c.getId() == comment.getId());
    }
}