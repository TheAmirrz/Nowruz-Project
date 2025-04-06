package com.example.geniusapp.util;

import com.example.geniusapp.content.Song;
import com.example.geniusapp.content.Album;
import com.example.geniusapp.core.user.Account;
import com.example.geniusapp.core.user.Admin;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.core.user.User;
import com.example.geniusapp.data.*;
import com.example.geniusapp.data.impl.*;
import com.example.geniusapp.interaction.Comment;
import com.example.geniusapp.interaction.Follow;

import java.util.*;
import java.util.stream.Collectors;

public class Platform {
    private static Platform instance;

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final CommentRepository commentRepository;
    private final FollowRepository followRepository;
    // Add other repository interfaces here

    private Platform() {
        // Use in-memory repositories by default
        this.userRepository = new InMemoryUserRepository();
        this.adminRepository = new InMemoryAdminRepository();
        this.artistRepository = new InMemoryArtistRepository();
        this.songRepository = new InMemorySongRepository();
        this.albumRepository = new InMemoryAlbumRepository();
        this.commentRepository = new InMemoryCommentRepository();
        this.followRepository = new InMemoryFollowRepository();
        // Initialize other in-memory repositories
        initialize();
    }

    public static synchronized Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public void initialize() {
        // Initialize your in-memory data using the repositories
        if (userRepository.findByUsername("listener1").isEmpty()) {
            createUser("listener1", "listener1@example.com", "pass1");
        }
        if (userRepository.findByUsername("listener2").isEmpty()) {
            createUser("listener2", "listener2@example.com", "pass3");
        }
        if (artistRepository.findByUsername("artist1").isEmpty()) {
            createArtist("artist1", "artist1@example.com", "pass2", "Famous singer");
        }
        if (songRepository.findByTitleAndArtistUsername("Song Two", "artist1").isEmpty()) {
            createSong("Song Two", "Lyrics for song two...", "Pop", new ArrayList<>(), new Date(), "artist1", null); // Added null for album
        }
        if (songRepository.findByTitleAndArtistUsername("Song One", "artist1").isEmpty()) {
            createSong("Song One", "Lyrics for song one...", "Rock", new ArrayList<>(), new Date(), "artist1", null); // Added null for album
        }
        if (albumRepository.findByTitleAndArtistUsername("Greatest Hits", "artist1").isEmpty()) {
            createAlbum("Greatest Hits", new Date(), "Pop", "artist1");
        }
        if (commentRepository.findByContentAndSongTitleAndUsername("I love this album!", "Song Two", "listener2").isEmpty()) {
            addComment("listener2", "Song Two", "I love this album!");
        }
        if (followRepository.findByUserIdAndArtistUsername(findUserByUsername("listener1").getId(), "artist1").isEmpty()) {
            followArtist("listener1", "artist1");
        }
        // ... (rest of your initialize logic using the repositories)
    }

    // User related methods
    public User createUser(String username, String email, String password) {
        Date memberSince = new Date();
        User user = new User(0, username, email, password);
        return userRepository.save(user);
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(userRepository.findAll());
        accounts.addAll(artistRepository.findAll());
        return accounts;
    }

    // Artist related methods
    public Artist createArtist(String username, String email, String password, String bio) {
        Date memberSince = new Date();
        Artist artist = new Artist(0, username, email, password, bio);
        return artistRepository.save(artist);
    }

    public Optional<Artist> findArtistById(int id) {
        return artistRepository.findById(id);
    }

    public Artist findArtistByUsername(String username) {
        return artistRepository.findByUsername(username).orElse(null);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    // Song related methods
    public Song createSong(String title, String lyrics, String genre, List<String> tags, Date releaseDate, String artistUsername, String albumTitle) {
        Artist artist = findArtistByUsername(artistUsername);
        Album album = null;
        if (albumTitle != null) {
            album = getAlbumByTitleAndArtist(albumTitle, artistUsername);
        }
        if (artist != null) {
            Song song = new Song(0, title, lyrics, genre, tags, artist, album, releaseDate); // Assuming id will be generated or set by the repository
            return songRepository.save(song);
        }
        return null;
    }

    public Optional<Song> findSongById(int id) {
        return songRepository.findById(id);
    }

    public Song findSongByTitleAndArtist(String title, String artistUsername) {
        return songRepository.findByTitleAndArtistUsername(title, artistUsername).orElse(null);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public List<Song> getSongsByArtist(String artistUsername) {
        return songRepository.findByArtistUsername(artistUsername);
    }

    // Album related methods
    // Album related methods
    public Album createAlbum(String title, Date releaseDate, String genre, String artistUsername) {
        Artist artist = findArtistByUsername(artistUsername);
        if (artist != null) {
            Album album = new Album(0, title, null, releaseDate, artist, genre); // Assuming id will be generated or set by the repository, and using null for coverArt
            return albumRepository.save(album);
        }
        return null;
    }

    public Optional<Album> findAlbumById(int id) {
        return albumRepository.findById(id);
    }

    public Album getAlbumByTitleAndArtist(String title, String artistUsername) {
        return albumRepository.findByTitleAndArtistUsername(title, artistUsername).orElse(null);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public List<Album> getAlbumsByArtist(String artistUsername) {
        return albumRepository.findByArtistUsername(artistUsername);
    }

    // Comment related methods
    public Comment addComment(String username, String songTitle, String content) {
        User user = findUserByUsername(username);
        Song song = findSongByTitleAndArtist(songTitle, findSongArtistUsername(songTitle));
        if (user != null && song != null) {
            Comment comment = new Comment(0, content, user, song);
            return commentRepository.save(comment);
        }
        return null;
    }

    public Optional<Comment> findCommentById(int id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsForSong(String songTitle, String artistUsername) {
        Song song = findSongByTitleAndArtist(songTitle, artistUsername);
        if (song != null) {
            return commentRepository.findBySongId(song.getId());
        }
        return Collections.emptyList();
    }

    public Comment findCommentByContent(String content, String songTitle, String username) {
        return commentRepository.findByContentAndSongTitleAndUsername(content, songTitle, username).orElse(null);
    }

    // Follow related methods
    public Follow followArtist(String listenerUsername, String artistUsername) {
        User listener = findUserByUsername(listenerUsername);
        Artist artist = findArtistByUsername(artistUsername);
        if (listener != null && artist != null) {
            Follow follow = new Follow(listener, artist);
            return followRepository.save(follow);
        }
        return null;
    }

    public void unfollowArtist(String listenerUsername, String artistUsername) {
        User listener = findUserByUsername(listenerUsername);
        Artist artist = findArtistByUsername(artistUsername);
        if (listener != null && artist != null) {
            followRepository.deleteByUserIdAndArtistId(listener.getId(), artist.getId());
        }
    }

    public List<Artist> getFollowedArtists(String username) {
        User user = findUserByUsername(username);
        if (user != null) {
            return artistRepository.findAllFollowedBy(user.getId());
        }
        return Collections.emptyList();
    }

    // Helper method to get artist username from song title (assuming unique title per artist for simplicity)
    private String findSongArtistUsername(String songTitle) {
        Optional<Song> songOptional = songRepository.findByTitleAndArtistUsername(songTitle, ""); // Artist username not needed here for lookup
        return songOptional.map(song -> {
            List<Artist> artists = song.getArtists();
            if (artists != null && !artists.isEmpty()) {
                return artists.get(0).getUsername(); // Get the username of the first artist in the list
            }
            return null; // Or handle the case where there are no artists for the song
        }).orElse(null);
    }

    // Update other helper methods if needed
    public List<Song> getAllSongsByArtist(String artistUsername) {
        return songRepository.findByArtistUsername(artistUsername);
    }


    public List<Comment> getAllCommentsForSong(String songTitle, String artistUsername) {
        Song song = findSongByTitleAndArtist(songTitle, artistUsername);
        if (song != null) {
            return commentRepository.findBySongId(song.getId());
        }
        return Collections.emptyList();
    }
    // Authentication method
    public Account authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return userOptional.get();
        }

        Optional<Artist> artistOptional = artistRepository.findByUsername(username);
        if (artistOptional.isPresent() && artistOptional.get().getPassword().equals(password)) {
            return artistOptional.get();
        }

        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
        if (adminOptional.isPresent() && adminOptional.get().getPassword().equals(password)) {
            return adminOptional.get();
        }

        return null; // Authentication failed
    }
    public List<Song> searchSongs(String query) {
        return songRepository.findByTitleContaining(query);
    }
    public List<Album> searchAlbums(String query) {
        return albumRepository.findByTitleContaining(query);
    }
}