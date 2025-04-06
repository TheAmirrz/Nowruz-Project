package com.example.geniusapp.data.impl;

import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.core.user.User;
import com.example.geniusapp.data.FollowRepository;
import com.example.geniusapp.interaction.Follow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryFollowRepository implements FollowRepository {
    private final List<Follow> follows = new ArrayList<>();

    @Override
    public Optional<Follow> findByUserIdAndArtistId(int userId, int artistId) {
        return follows.stream()
                .filter(follow -> follow.getUser().getId() == userId && follow.getArtist().getId() == artistId)
                .findFirst();
    }

    @Override
    public Optional<Follow> findByUserIdAndArtistUsername(int userId, String artistUsername) {
        return follows.stream()
                .filter(follow -> follow.getUser().getId() == userId && follow.getArtist().getUsername().equals(artistUsername))
                .findFirst();
    }

    @Override
    public List<Follow> findAll() {
        return new ArrayList<>(follows);
    }

    @Override
    public Follow save(Follow follow) {
        follows.add(follow);
        return follow;
    }

    @Override
    public void deleteByUserIdAndArtistId(int userId, int artistId) {
        follows.removeIf(follow -> follow.getUser().getId() == userId && follow.getArtist().getId() == artistId);
    }

    @Override
    public List<Artist> findAllFollowedBy(int userId) {
        return follows.stream()
                .filter(follow -> follow.getUser().getId() == userId)
                .map(Follow::getArtist)
                .collect(Collectors.toList());
    }
}