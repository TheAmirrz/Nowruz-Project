package com.example.geniusapp.data;

import com.example.geniusapp.content.Song;
import java.util.Optional;
import java.util.List;

public interface SongRepository {
    Optional<Song> findById(int id);
    Optional<Song> findByTitleAndArtistUsername(String title, String artistUsername);
    List<Song> findAll();
    Song save(Song song);
    void delete(Song song);
    List<Song> findByArtistUsername(String artistUsername);
    List<Song> findByTitleContaining(String query);
    // Add other song-specific methods if needed

}