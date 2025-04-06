package com.example.geniusapp.data;

import com.example.geniusapp.interaction.Follow;
import java.util.List;
import java.util.Optional;

public interface FollowRepository {
    Optional<Follow> findByUserIdAndArtistUsername(int userId, String artistUsername);
    List<Follow> findAll();
    Follow save(Follow follow);
    void deleteByUserIdAndArtistId(int userId, int artistId);
    List<com.example.geniusapp.core.user.Artist> findAllFollowedBy(int userId);
    Optional<Follow> findByUserIdAndArtistId(int userId, int artistId);
}