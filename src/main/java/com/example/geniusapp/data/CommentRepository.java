package com.example.geniusapp.data;

import com.example.geniusapp.interaction.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(int id);
    List<Comment> findBySongId(int songId);
    Optional<Comment> findByContentAndSongTitleAndUsername(String content, String songTitle, String username);
    List<Comment> findAll();
    Comment save(Comment comment);
    void delete(Comment comment);
}