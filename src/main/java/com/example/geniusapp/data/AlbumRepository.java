package com.example.geniusapp.data;

import com.example.geniusapp.content.Album;
import java.util.List;
import java.util.Optional;

public interface AlbumRepository {
    Optional<Album> findById(int id);
    Optional<Album> findByTitleAndArtistUsername(String title, String artistUsername);
    List<Album> findAll();
    Album save(Album album);
    void delete(Album album);
    List<Album> findByArtistUsername(String artistUsername);
    List<Album> findByTitleContaining(String query);

}