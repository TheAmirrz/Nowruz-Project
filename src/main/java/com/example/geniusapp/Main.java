//package com.example.geniusapp;
//
//import com.example.geniusapp.content.Song;
//import com.example.geniusapp.content.Album;
//import com.example.geniusapp.util.Platform;
//import com.example.geniusapp.core.user.Artist;
//import com.example.geniusapp.core.user.User;
//import com.example.geniusapp.interaction.Comment;
//import com.example.geniusapp.interaction.Follow;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//public class Main {
//
//    private static final Platform platform = Platform.getInstance();
//    private static final Scanner scanner = new Scanner(System.in);
//    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//
//    public static void main(String[] args) {
//        System.out.println("Welcome to the Genius App Platform!");
//
//        while (true) {
//            printMainMenu();
//            System.out.print("Enter your choice: ");
//            String choice = scanner.nextLine().trim();
//
//            switch (choice) {
//                case "1":
//                    createUser();
//                    break;
//                case "2":
//                    createArtist();
//                    break;
//                case "3":
//                    createSong();
//                    break;
//                case "4":
//                    createAlbum();
//                    break;
//                case "5":
//                    addComment();
//                    break;
//                case "6":
//                    followArtist();
//                    break;
//                case "7":
//                    listAllUsers();
//                    break;
//                case "8":
//                    listAllArtists();
//                    break;
//                case "9":
//                    listAllSongs();
//                    break;
//                case "10":
//                    listAllAlbums();
//                    break;
//                case "11":
//                    listCommentsForSong();
//                    break;
//                case "12":
//                    listFollowedArtists();
//                    break;
//                case "13":
//                    searchSongs();
//                    break;
//                case "14":
//                    searchAlbums();
//                    break;
//                case "0":
//                    System.out.println("Exiting the Genius App Platform. Goodbye!");
//                    scanner.close();
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//            System.out.println(); // Add an empty line for better readability
//        }
//    }
//
//    private static void printMainMenu() {
//        System.out.println("\n--- Main Menu ---");
//        System.out.println("1. Create User");
//        System.out.println("2. Create Artist");
//        System.out.println("3. Create Song");
//        System.out.println("4. Create Album");
//        System.out.println("5. Add Comment to Song");
//        System.out.println("6. Follow Artist");
//        System.out.println("--- Lists ---");
//        System.out.println("7. List All Users");
//        System.out.println("8. List All Artists");
//        System.out.println("9. List All Songs");
//        System.out.println("10. List All Albums");
//        System.out.println("11. List Comments for a Song");
//        System.out.println("12. List Followed Artists by User");
//        System.out.println("--- Search ---");
//        System.out.println("13. Search Songs");
//        System.out.println("14. Search Albums");
//        System.out.println("--- Exit ---");
//        System.out.println("0. Exit");
//    }
//
//    private static void createUser() {
//        System.out.println("\n--- Create User ---");
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine().trim();
//        System.out.print("Enter email: ");
//        String email = scanner.nextLine().trim();
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine().trim();
//        User user = platform.createUser(username, email, password);
//        if (user != null) {
//            System.out.println("User created successfully with username: " + user.getUsername());
//        } else {
//            System.out.println("Failed to create user.");
//        }
//    }
//
//    private static void createArtist() {
//        System.out.println("\n--- Create Artist ---");
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine().trim();
//        System.out.print("Enter email: ");
//        String email = scanner.nextLine().trim();
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine().trim();
//        System.out.print("Enter bio: ");
//        String bio = scanner.nextLine().trim();
//        Artist artist = platform.createArtist(username, email, password, bio);
//        if (artist != null) {
//            System.out.println("Artist created successfully with username: " + artist.getUsername());
//        } else {
//            System.out.println("Failed to create artist.");
//        }
//    }
//
//    private static void createSong() {
//        System.out.println("\n--- Create Song ---");
//        System.out.print("Enter song title: ");
//        String title = scanner.nextLine().trim();
//        System.out.print("Enter lyrics: ");
//        String lyrics = scanner.nextLine().trim();
//        System.out.print("Enter genre: ");
//        String genre = scanner.nextLine().trim();
//        System.out.print("Enter tags (comma-separated): ");
//        List<String> tags = Arrays.asList(scanner.nextLine().trim().split(",")).stream().map(String::trim).collect(Collectors.toList());
//        System.out.print("Enter release date (yyyy-MM-dd): ");
//        String releaseDateStr = scanner.nextLine().trim();
//        Date releaseDate = null;
//        try {
//            releaseDate = dateFormatter.parse(releaseDateStr);
//        } catch (ParseException e) {
//            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
//            return;
//        }
//        System.out.print("Enter artist username: ");
//        String artistUsername = scanner.nextLine().trim();
//        System.out.print("Enter album title (optional, leave blank if none): ");
//        String albumTitle = scanner.nextLine().trim();
//
//        Song song = platform.createSong(title, lyrics, genre, tags, releaseDate, artistUsername, albumTitle.isEmpty() ? null : albumTitle);
//        if (song != null) {
//            System.out.println("Song created successfully: " + song.getTitle() + " by " + artistUsername);
//        } else {
//            System.out.println("Failed to create song. Ensure the artist and album (if provided) exist.");
//        }
//    }
//
//    private static void createAlbum() {
//        System.out.println("\n--- Create Album ---");
//        System.out.print("Enter album title: ");
//        String title = scanner.nextLine().trim();
//        System.out.print("Enter release date (yyyy-MM-dd): ");
//        String releaseDateStr = scanner.nextLine().trim();
//        Date releaseDate = null;
//        try {
//            releaseDate = dateFormatter.parse(releaseDateStr);
//        } catch (ParseException e) {
//            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
//            return;
//        }
//        System.out.print("Enter genre: ");
//        String genre = scanner.nextLine().trim();
//        System.out.print("Enter artist username: ");
//        String artistUsername = scanner.nextLine().trim();
//
//        Album album = platform.createAlbum(title, releaseDate, genre, artistUsername);
//        if (album != null) {
//            System.out.println("Album created successfully: " + album.getTitle() + " by " + artistUsername);
//        } else {
//            System.out.println("Failed to create album. Ensure the artist exists.");
//        }
//    }
//
//    private static void addComment() {
//        System.out.println("\n--- Add Comment to Song ---");
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine().trim();
//        System.out.print("Enter song title: ");
//        String songTitle = scanner.nextLine().trim();
//        System.out.print("Enter comment content: ");
//        String content = scanner.nextLine().trim();
//
//        Comment comment = platform.addComment(username, songTitle, content);
//        if (comment != null) {
//            System.out.println("Comment added successfully by " + username + " to song: " + songTitle);
//        } else {
//            System.out.println("Failed to add comment. Ensure the user and song exist.");
//        }
//    }
//
//    private static void followArtist() {
//        System.out.println("\n--- Follow Artist ---");
//        System.out.print("Enter your username (listener): ");
//        String listenerUsername = scanner.nextLine().trim();
//        System.out.print("Enter the username of the artist to follow: ");
//        String artistUsername = scanner.nextLine().trim();
//
//        Follow follow = platform.followArtist(listenerUsername, artistUsername);
//        if (follow != null) {
//            System.out.println(listenerUsername + " is now following " + artistUsername);
//        } else {
//            System.out.println("Failed to follow artist. Ensure both user and artist exist.");
//        }
//    }
//
//    private static void listAllUsers() {
//        System.out.println("\n--- All Users ---");
//        List<User> users = platform.getAllUsers();
//        if (users.isEmpty()) {
//            System.out.println("No users found.");
//        } else {
//            users.forEach(user -> System.out.println("- " + user.getUsername() + " (" + user.getEmail() + ")"));
//        }
//    }
//
//    private static void listAllArtists() {
//        System.out.println("\n--- All Artists ---");
//        List<Artist> artists = platform.getAllArtists();
//        if (artists.isEmpty()) {
//            System.out.println("No artists found.");
//        } else {
//            artists.forEach(artist -> System.out.println("- " + artist.getUsername() + " (" + artist.getEmail() + ") - Bio: " + artist.getBio()));
//        }
//    }
//
//    private static void listAllSongs() {
//        System.out.println("\n--- All Songs ---");
//        List<Song> songs = platform.getAllSongs();
//        if (songs.isEmpty()) {
//            System.out.println("No songs found.");
//        } else {
//            songs.forEach(song -> System.out.println("- " + song.getTitle() + " by " + song.getArtists().stream().map(Artist::getUsername).collect(Collectors.joining(", ")) + " (" + song.getGenre() + ")"));
//        }
//    }
//
//    private static void listAllAlbums() {
//        System.out.println("\n--- All Albums ---");
//        List<Album> albums = platform.getAllAlbums();
//        if (albums.isEmpty()) {
//            System.out.println("No albums found.");
//        } else {
//            albums.forEach(album -> System.out.println("- " + album.getTitle() + " by " + album.getArtist().getUsername() + " (" + album.getGenre() + ")"));
//        }
//    }
//
//    private static void listCommentsForSong() {
//        System.out.println("\n--- Comments for a Song ---");
//        System.out.print("Enter song title: ");
//        String songTitle = scanner.nextLine().trim();
//        System.out.print("Enter artist username of the song: ");
//        String artistUsername = scanner.nextLine().trim();
//        List<Comment> comments = platform.getCommentsForSong(songTitle, artistUsername);
//        if (comments.isEmpty()) {
//            System.out.println("No comments found for the song: " + songTitle);
//        } else {
//            System.out.println("Comments for \"" + songTitle + "\" by " + artistUsername + ":");
//            comments.forEach(comment -> System.out.println("- " + comment.getUser().getUsername() + ": " + comment.getContent() + " (" + comment.getTimestamp() + ")"));
//        }
//    }
//
//    private static void listFollowedArtists() {
//        System.out.println("\n--- Followed Artists by User ---");
//        System.out.print("Enter your username: ");
//        String username = scanner.nextLine().trim();
//        List<Artist> followedArtists = platform.getFollowedArtists(username);
//        if (followedArtists.isEmpty()) {
//            System.out.println("No artists followed by " + username + ".");
//        } else {
//            System.out.println("Artists followed by " + username + ":");
//            followedArtists.forEach(artist -> System.out.println("- " + artist.getUsername() + " (" + artist.getEmail() + ")"));
//        }
//    }
//
//    private static void searchSongs() {
//        System.out.println("\n--- Search Songs ---");
//        System.out.print("Enter your search query: ");
//        String query = scanner.nextLine().trim();
//        // Assuming you have a search functionality in your Platform
//        // List<Song> results = platform.searchSongs(query);
//        System.out.println("Search functionality for songs is not yet implemented in this basic example.");
//    }
//
//    private static void searchAlbums() {
//        System.out.println("\n--- Search Albums ---");
//        System.out.print("Enter your search query: ");
//        String query = scanner.nextLine().trim();
//        // Assuming you have a search functionality in your Platform
//        // List<Album> results = platform.searchAlbums(query);
//        System.out.println("Search functionality for albums is not yet implemented in this basic example.");
//    }
//}
package com.example.geniusapp;

import com.example.geniusapp.content.Song;
import com.example.geniusapp.content.Album;
import com.example.geniusapp.core.user.Account;
import com.example.geniusapp.util.Platform;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.core.user.User;
import com.example.geniusapp.interaction.Comment;
import com.example.geniusapp.interaction.Follow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final Platform platform = Platform.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static Account loggedInUser = null;
    public static void main(String[] args) {
        System.out.println("Welcome to the Genius App Platform!");

        while (loggedInUser == null) {
            if (!authenticateUser()) {
                System.out.println("Authentication failed. Please try again.");
            }
        }

        System.out.println("Login successful. Welcome, " + loggedInUser.getUsername() + "!");

        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                System.out.println("Exiting the Genius App Platform. Goodbye!");
                scanner.close();
                return;
            }

            processChoice(choice);
            System.out.println(); // Add an empty line for better readability
        }
    }

    private static boolean authenticateUser() {
        System.out.println("\n--- Authentication ---");
        System.out.println("1. Login");
        System.out.println("2. Register as Listener");
        System.out.println("3. Register as Artist");
        System.out.print("Enter your choice: ");
        String authChoice = scanner.nextLine().trim();

        switch (authChoice) {
            case "1":
                return login();
            case "2":
                registerUser(false); // Register as Listener
                return false; // Go back to authentication prompt
            case "3":
                registerUser(true); // Register as Artist
                return false; // Go back to authentication prompt
            default:
                System.out.println("Invalid choice.");
                return false;
        }
    }

    private static boolean login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        Account user = platform.authenticateUser(username, password); // Changed type to Account

        if (user != null) {
            loggedInUser = user;
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private static void registerUser(boolean isArtist) {
        System.out.println("\n--- Register as " + (isArtist ? "Artist" : "Listener") + " ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (isArtist) {
            System.out.print("Enter bio: ");
            String bio = scanner.nextLine().trim();
            Artist artist = platform.createArtist(username, email, password, bio);
            if (artist != null) {
                System.out.println("Artist registered successfully with username: " + artist.getUsername());
            } else {
                System.out.println("Failed to register artist.");
            }
        } else {
            User user = platform.createUser(username, email, password);
            if (user != null) {
                System.out.println("Listener registered successfully with username: " + user.getUsername());
            } else {
                System.out.println("Failed to register listener.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("Logged in as: " + loggedInUser.getUsername() + " (" + loggedInUser.getClass().getSimpleName() + ")");

        if (loggedInUser instanceof Artist) {
            System.out.println("\n--- Artist Features ---");
            System.out.println("1. Create Song");
            System.out.println("2. Create Album");
        }

        System.out.println("\n--- General Features ---");
        System.out.println((loggedInUser instanceof Artist ? 3 : 1) + ". Add Comment to Song");
        System.out.println((loggedInUser instanceof Artist ? 4 : 2) + ". Follow Artist");
        System.out.println((loggedInUser instanceof Artist ? 5 : 3) + ". List All Songs");
        System.out.println((loggedInUser instanceof Artist ? 6 : 4) + ". List All Albums");
        System.out.println((loggedInUser instanceof Artist ? 7 : 5) + ". List Comments for a Song");
        System.out.println((loggedInUser instanceof Artist ? 8 : 6) + ". List Followed Artists by User");
        System.out.println((loggedInUser instanceof Artist ? 9 : 7) + ". Search Songs");
        System.out.println((loggedInUser instanceof Artist ? 10 : 8) + ". Search Albums");

        System.out.println("\n--- Account Management ---");
        System.out.println((loggedInUser instanceof Artist ? 11 : 9) + ". View My Profile"); // Example
        System.out.println("0. Logout and Exit");

        // Admin features can be added here if you have an Admin user type
        // if (loggedInUser instanceof Admin) {
        //     System.out.println("\n--- Admin Features ---");
        //     System.out.println("...");
        // }
    }

    private static void processChoice(String choiceStr) {
        try {
            int choice = Integer.parseInt(choiceStr);
            if (loggedInUser instanceof Artist) {
                processArtistChoice(choice);
            } else {
                processListenerChoice(choice);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number from the menu.");
        }
    }

    private static void processArtistChoice(int choice) {
        switch (choice) {
            case 1:
                createSong();
                break;
            case 2:
                createAlbum();
                break;
            case 3:
                addComment();
                break;
            case 4:
                followArtist();
                break;
            case 5:
                listAllSongs();
                break;
            case 6:
                listAllAlbums();
                break;
            case 7:
                listCommentsForSong();
                break;
            case 8:
                listFollowedArtists();
                break;
            case 9:
                searchSongs();
                break;
            case 10:
                searchAlbums();
                break;
            case 11:
                viewMyProfile();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void processListenerChoice(int choice) {
        switch (choice) {
            case 1:
                addComment();
                break;
            case 2:
                followArtist();
                break;
            case 3:
                listAllSongs();
                break;
            case 4:
                listAllAlbums();
                break;
            case 5:
                listCommentsForSong();
                break;
            case 6:
                listFollowedArtists();
                break;
            case 7:
                searchSongs();
                break;
            case 8:
                searchAlbums();
                break;
            case 9:
                viewMyProfile();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void viewMyProfile() {
        System.out.println("\n--- My Profile ---");
        System.out.println("Username: " + loggedInUser.getUsername());
        System.out.println("Email: " + loggedInUser.getEmail());
        System.out.println("Account Type: " + loggedInUser.getClass().getSimpleName());
        if (loggedInUser instanceof Artist) {
            System.out.println("Bio: " + ((Artist) loggedInUser).getBio());
        }
    }

    // --- Existing methods (createUser, createArtist, createSong, createAlbum, addComment, followArtist,
    //     listAllUsers, listAllArtists, listAllSongs, listAllAlbums, listCommentsForSong,
    //     listFollowedArtists, searchSongs, searchAlbums) remain largely the same but might need minor adjustments
    //     if you decide to restrict creation or listing based on user roles in the Platform class itself.

    private static void createUser() {
        System.out.println("\n--- Create User (Admin Only) ---");
        // This should ideally be an admin-only function
        if (loggedInUser != null) {
            System.out.println("This action is not permitted for your current role.");
            return;
        }
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        User user = platform.createUser(username, email, password);
        if (user != null) {
            System.out.println("User created successfully with username: " + user.getUsername());
        } else {
            System.out.println("Failed to create user.");
        }
    }

    private static void createArtist() {
        System.out.println("\n--- Create Artist (Admin Only) ---");
        // This should ideally be an admin-only function
        if (loggedInUser != null) {
            System.out.println("This action is not permitted for your current role.");
            return;
        }
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter bio: ");
        String bio = scanner.nextLine().trim();
        Artist artist = platform.createArtist(username, email, password, bio);
        if (artist != null) {
            System.out.println("Artist created successfully with username: " + artist.getUsername());
        } else {
            System.out.println("Failed to create artist.");
        }
    }

    private static void createSong() {
        if (!(loggedInUser instanceof Artist)) {
            System.out.println("Only artists can create songs.");
            return;
        }
        System.out.println("\n--- Create Song ---");
        System.out.print("Enter song title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter lyrics: ");
        String lyrics = scanner.nextLine().trim();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine().trim();
        System.out.print("Enter tags (comma-separated): ");
        List<String> tags = Arrays.asList(scanner.nextLine().trim().split(",")).stream().map(String::trim).collect(Collectors.toList());
        System.out.print("Enter release date (yyyy-MM-dd): ");
        String releaseDateStr = scanner.nextLine().trim();
        Date releaseDate = null;
        try {
            releaseDate = dateFormatter.parse(releaseDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }
        System.out.print("Enter album title (optional, leave blank if none): ");
        String albumTitle = scanner.nextLine().trim();

        Song song = platform.createSong(title, lyrics, genre, tags, releaseDate, loggedInUser.getUsername(), albumTitle.isEmpty() ? null : albumTitle);
        if (song != null) {
            System.out.println("Song created successfully: " + song.getTitle());
        } else {
            System.out.println("Failed to create song. Ensure the album (if provided) exists.");
        }
    }

    private static void createAlbum() {
        if (!(loggedInUser instanceof Artist)) {
            System.out.println("Only artists can create albums.");
            return;
        }
        System.out.println("\n--- Create Album ---");
        System.out.print("Enter album title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter release date (yyyy-MM-dd): ");
        String releaseDateStr = scanner.nextLine().trim();
        Date releaseDate = null;
        try {
            releaseDate = dateFormatter.parse(releaseDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine().trim();

        Album album = platform.createAlbum(title, releaseDate, genre, loggedInUser.getUsername());
        if (album != null) {
            System.out.println("Album created successfully: " + album.getTitle());
        } else {
            System.out.println("Failed to create album.");
        }
    }

    private static void addComment() {
        System.out.println("\n--- Add Comment to Song ---");
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine().trim();
        System.out.print("Enter artist username of the song: ");
        String artistUsername = scanner.nextLine().trim();
        System.out.print("Enter comment content: ");
        String content = scanner.nextLine().trim();

        Comment comment = platform.addComment(loggedInUser.getUsername(), songTitle, content);
        if (comment != null) {
            System.out.println("Comment added successfully to song: " + songTitle);
        } else {
            System.out.println("Failed to add comment. Ensure the song exists.");
        }
    }

    private static void followArtist() {
        System.out.println("\n--- Follow Artist ---");
        System.out.print("Enter the username of the artist to follow: ");
        String artistUsername = scanner.nextLine().trim();

        Follow follow = platform.followArtist(loggedInUser.getUsername(), artistUsername);
        if (follow != null) {
            System.out.println("You are now following " + artistUsername);
        } else {
            System.out.println("Failed to follow artist. Ensure the artist exists.");
        }
    }

    private static void listAllSongs() {
        System.out.println("\n--- All Songs ---");
        List<Song> songs = platform.getAllSongs();
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
        } else {
            songs.forEach(song -> System.out.println("- " + song.getTitle() + " by " + song.getArtists().stream().map(Artist::getUsername).collect(Collectors.joining(", ")) + " (" + song.getGenre() + ")"));
        }
    }

    private static void listAllAlbums() {
        System.out.println("\n--- All Albums ---");
        List<Album> albums = platform.getAllAlbums();
        if (albums.isEmpty()) {
            System.out.println("No albums found.");
        } else {
            albums.forEach(album -> System.out.println("- " + album.getTitle() + " by " + album.getArtist().getUsername() + " (" + album.getGenre() + ")"));
        }
    }

    private static void listCommentsForSong() {
        System.out.println("\n--- Comments for a Song ---");
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine().trim();
        System.out.print("Enter artist username of the song: ");
        String artistUsername = scanner.nextLine().trim();
        List<Comment> comments = platform.getCommentsForSong(songTitle, artistUsername);
        if (comments.isEmpty()) {
            System.out.println("No comments found for the song: " + songTitle);
        } else {
            System.out.println("Comments for \"" + songTitle + "\" by " + artistUsername + ":");
            comments.forEach(comment -> System.out.println("- " + comment.getUser().getUsername() + ": " + comment.getContent() + " (" + comment.getTimestamp() + ")"));
        }
    }

    private static void listFollowedArtists() {
        System.out.println("\n--- Followed Artists ---");
        List<Artist> followedArtists = platform.getFollowedArtists(loggedInUser.getUsername());
        if (followedArtists.isEmpty()) {
            System.out.println("No artists followed by " + loggedInUser.getUsername() + ".");
        } else {
            System.out.println("Artists followed by " + loggedInUser.getUsername() + ":");
            followedArtists.forEach(artist -> System.out.println("- " + artist.getUsername() + " (" + artist.getEmail() + ")"));
        }
    }

    private static void searchSongs() {
        System.out.println("\n--- Search Songs ---");
        System.out.print("Enter your search query: ");
        String query = scanner.nextLine().trim();
        List<Song> results = platform.searchSongs(query);
        if (results.isEmpty()) {
            System.out.println("No songs found matching your query.");
        } else {
            System.out.println("Search results for songs:");
            results.forEach(song -> System.out.println("- " + song.getTitle() + " by " + song.getArtists().stream().map(Artist::getUsername).collect(Collectors.joining(", ")) + " (" + song.getGenre() + ")"));
        }
    }

    private static void searchAlbums() {
        System.out.println("\n--- Search Albums ---");
        System.out.print("Enter your search query: ");
        String query = scanner.nextLine().trim();
        List<Album> results = platform.searchAlbums(query);
        if (results.isEmpty()) {
            System.out.println("No albums found matching your query.");
        } else {
            System.out.println("Search results for albums:");
            results.forEach(album -> System.out.println("- " + album.getTitle() + " by " + album.getArtist().getUsername() + " (" + album.getGenre() + ")"));
        }
    }
}