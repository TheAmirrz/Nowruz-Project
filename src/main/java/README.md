# GeniusApp

## Project Description

GeniusApp is a platform designed for music enthusiasts to discover, manage, and interact with their favorite songs, artists, and albums. This application provides a command-line interface for users to explore a music library, search for content, and potentially interact with other users and artists.

## Features

Here's a list of the core features currently implemented in GeniusApp:

* **User Authentication:** Allows users (including artists and potentially admins) to log in to the application using their username and password.
* **User Creation:** Functionality to create new user accounts.
* **Artist Creation:** Ability to register new artist profiles on the platform.
* **Song Management:**
    * Creation of new songs with details such as title, lyrics, genre, tags, release date, associated artist, and album.
    * Listing all songs available in the library.
    * Retrieving all songs by a specific artist.
* **Album Management:**
    * Creation of new albums with a title.
* **Search Functionality:**
    * Searching for songs by title (case-insensitive).
    * Searching for albums by title (case-insensitive).
* **Commenting:** Basic functionality to add comments to songs.
* **Following Artists:** Users can follow artists on the platform.

## Bonus Features (Potential Future Enhancements)

Here are some additional features that could be implemented to further enhance GeniusApp:

* **User Registration:** A more formal process for new users to sign up.
* **Admin User Role:** Implementation of an administrator role with special privileges for managing the platform.
* **Detailed User Profiles:** Allowing users and artists to have more comprehensive profiles.
* **Advanced Search:** Expanding search capabilities to include searching by artist, genre, tags, or album.
* **Display Song Lyrics:** Functionality to view the lyrics of a song.
* **User Playlists:** Allowing users to create and manage their own playlists.
* **Rating and Reviewing:** Enabling users to rate and review songs and albums.
* **Media Playback:** Integrating the ability to play song previews or full songs.
* **Robust Error Handling:** Implementing more comprehensive error handling and input validation throughout the application.
* **Database Persistence:** If the current implementation uses in-memory storage, transitioning to a database (like MySQL or PostgreSQL) for persistent data storage.
* **User Roles and Permissions:** A more granular system for managing user roles and permissions.

## How to Run the Project

To run the GeniusApp project, you need to have the Java Development Kit (JDK) installed on your system. Follow these steps:

1.  **Clone the Repository:** If the project is hosted on a version control system like Git, clone the repository to your local machine.
2.  **Navigate to the Project Directory:** Open your terminal or command prompt and navigate to the root directory of the GeniusApp project.
3.  **Compile the Source Files:** Compile the Java source files. You can do this using an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse, which typically handles the compilation process automatically. Alternatively, if you have a build tool like Maven or Gradle configured, you can use the appropriate commands (e.g., `mvn compile` for Maven, `gradle build` for Gradle).
4.  **Run the Application:** Execute the `Main` class to start the application. In most IDEs, you can do this by locating the `Main.java` file in the `src/main/java/com/example/geniusapp/main` directory and running it. From the terminal, you can navigate to the directory containing the compiled class files and run the command: `java com.example.geniusapp.main.Main`.
5.  **Interact with the Application:** Once the application starts, it will likely present a command-line interface where you can enter commands to interact with the features, such as logging in, creating users, searching for songs, etc. Follow the prompts displayed in the console.

## Project Structure

The project structure is organized into the following main packages:

```
└── 📁main
    └── 📁java
        └── 📁com
            └── 📁example
                └── 📁geniusapp
                    └── 📁content
                        └── Album.java
                        └── LyricEdit.java
                        └── Song.java
                    └── 📁core
                        └── 📁user
                            └── Account.java
                            └── Admin.java
                            └── Artist.java
                            └── User.java
                    └── 📁dao
                        └── AccountDAO.java
                        └── AlbumDAO.java
                        └── SongDAO.java
                    └── 📁data
                        └── AdminRepository.java
                        └── AlbumRepository.java
                        └── ArtistRepository.java
                        └── CommentRepository.java
                        └── FollowRepository.java
                        └── 📁impl
                            └── DatabaseAdminRepository.java
                            └── DatabaseAlbumRepository.java
                            └── DatabaseArtistRepository.java
                            └── DatabaseCommentRepository.java
                            └── DatabaseFollowRepository.java
                            └── DatabaseSongRepository.java
                            └── DatabaseUserRepository.java
                            └── InMemoryAdminRepository.java
                            └── InMemoryAlbumRepository.java
                            └── InMemoryArtistRepository.java
                            └── InMemoryCommentRepository.java
                            └── InMemoryFollowRepository.java
                            └── InMemorySongRepository.java
                            └── InMemoryUserRepository.java
                        └── SongRepository.java
                        └── UserRepository.java
                    └── 📁interaction
                        └── Comment.java
                        └── Follow.java
                        └── Notification.java
                    └── 📁interfaces
                        └── ContentContributor.java
                        └── Likeable.java
                        └── Searchable.java
                    └── Main.java
                    └── 📁service
                        └── AuthenticationService.java
                        └── NotificationService.java
                        └── RecommendationService.java
                    └── 📁ui
                        └── AlbumPage.java
                        └── SearchEngine.java
                        └── SongPage.java
                        └── UserPage.java
                    └── 📁util
                        └── Chart.java
                        └── LyricAnalysis.java
                        └── Platform.java
        └── README.md
    ├── resources
```
## Dependencies and Installation

To run this project, you need to have the following installed:

* **Java Development Kit (JDK):** Ensure you have a compatible version of the JDK installed on your system. You can download it from the official Oracle website or through other distributions like OpenJDK.

**Optional Dependencies (if using database persistence):**

* **Database System:** If the project is configured to use a database, you will need to have the specific database system installed (e.g., MySQL, PostgreSQL, H2).
* **JDBC Driver:** If you are using a database, you will need the corresponding JDBC driver for your database system. These drivers are usually added as dependencies in your project's build configuration file (e.g., `pom.xml` for Maven, `build.gradle` for Gradle).

**No specific external packages or libraries need to be installed manually beyond the standard Java library.** If you are using an IDE with build tool support (Maven or Gradle), the IDE will typically handle downloading and managing any external dependencies defined in your project's configuration files.

## Class Information, Use Cases, and Reasoning

Here's a breakdown of the classes in the GeniusApp project, their use cases, and the reasoning behind their existence:

**1. `content` Package:**

* **`album.Album.java`:**
  * **Use Case:** Represents a music album in the application. It stores information such as the album's ID, title, cover art, release date, the artist who created it, and the genre.
  * **Reasoning:** This class models the real-world concept of an album, allowing the application to organize songs into collections. It encapsulates all the relevant data associated with an album.

* **`Song.java`:**
  * **Use Case:** Represents a single song in the music library. It holds details like the song's ID, title, lyrics, genre, tags, a list of associated artists, the album it belongs to, the release date, the number of views, and any comments made on the song.
  * **Reasoning:** This is the core entity representing music in the application. It needs to store all pertinent information about a song to be useful for searching, displaying, and interacting with. The use of a `List` for artists acknowledges that a song can have multiple performers.

**2. `core` Package:**

* **`Platform.java`:**
  * **Use Case:** Acts as the central service layer of the application. It orchestrates all the core functionalities, including user authentication, managing users, artists, songs, and albums, and handling search requests. It interacts with the data repositories to persist and retrieve information.
  * **Reasoning:** This class provides a single point of access for the application's business logic. It promotes separation of concerns by isolating the core functionalities from the presentation layer (`Main` class) and the data access layer (repositories). This makes the application more organized, maintainable, and testable.

* **`user` Package:**
  * **`Account.java`:**
    * **Use Case:** An abstract base class that defines the common attributes for all types of user accounts in the system, such as username, email, and password.
    * **Reasoning:** This class serves as a blueprint for different user types, promoting code reuse and establishing a clear hierarchy for user-related entities. It ensures that all user types have the fundamental identification and authentication information.
  * **`Admin.java`:**
    * **Use Case:** Represents an administrator user, inheriting from `Account`. Admins would typically have additional privileges to manage the platform.
    * **Reasoning:** This class models a specific role within the application with elevated permissions, allowing for administrative tasks like managing users or content.
  * **`Artist.java`:**
    * **Use Case:** Represents a music artist, inheriting from `Account`. It includes additional information specific to artists, such as a biography and a list of users who follow them.
    * **Reasoning:** This class models the artists who create the music on the platform, recognizing their unique role and attributes.
  * **`User.java`:**
    * **Use Case:** Represents a regular user of the platform, inheriting from `Account`. These users can typically browse music, search, and interact with content.
    * **Reasoning:** This class models the standard users of the application who consume and interact with the music content.

**3. `data` Package:**

* **`AlbumRepository.java`:**
  * **Use Case:** An interface that defines the contract for how `Album` objects are accessed and managed in the data storage (whether in memory or a database). It declares methods for common operations like finding, saving, and deleting albums.
  * **Reasoning:** This interface provides an abstraction layer between the `Platform` (which needs to work with album data) and the actual implementation of data storage. This allows for flexibility in choosing or changing the underlying data storage without modifying the core logic.
* **`ArtistRepository.java`:**
  * **Use Case:** Similar to `AlbumRepository`, this interface defines the contract for accessing and managing `Artist` objects.
  * **Reasoning:** Provides an abstraction for artist data management.
* **`CommentRepository.java`:**
  * **Use Case:** Defines the contract for managing comments on songs.
  * **Reasoning:** Abstracts the operations related to storing and retrieving comments.
* **`FollowRepository.java`:**
  * **Use Case:** Defines the contract for managing the relationships between users and the artists they follow.
  * **Reasoning:** Abstracts the handling of follow data.
* **`SongRepository.java`:**
  * **Use Case:** Defines the contract for how `Song` objects are accessed and managed in the data storage.
  * **Reasoning:** Provides an abstraction for song data management.
* **`UserRepository.java`:**
  * **Use Case:** Defines the contract for accessing and managing `User` objects.
  * **Reasoning:** Abstracts the management of user account data.

**4. `main` Package:**

* **`Main.java`:**
  * **Use Case:** The entry point of the application. It contains the `main` method that starts the program and provides a command-line interface for users to interact with the GeniusApp platform. It handles user input and calls the appropriate methods in the `Platform` class to perform actions.
  * **Reasoning:** This class is responsible for the presentation layer of the application in this CLI-based implementation. It handles the user interface and directs user commands to the underlying business logic.

In summary, each class in the GeniusApp project has a specific responsibility and contributes to the overall functionality of the application. The design utilizes principles like modularity, abstraction, and separation of concerns to create a well-structured and maintainable codebase.
## Other Information

* The `Platform` class serves as the central component of the application's core logic, managing different aspects like user authentication, song and album management, and search functionalities.
* The `Main` class provides a command-line interface (CLI) for users to interact with the GeniusApp platform.
* The project utilizes the Repository pattern for data access, with interfaces defined in the `data` package and their implementations (potentially both in-memory and database-backed) residing in an `impl` sub-package (though not explicitly shown in the structure above).
* The `content` package houses the primary data entities of the application, such as `Song` and `Album`.
* The `core.user` package contains classes defining different types of users within the system, inheriting from a base `Account` class.

This README provides a comprehensive overview of the GeniusApp project. For more detailed information or to contribute, please refer to the project's source code and any additional documentation.