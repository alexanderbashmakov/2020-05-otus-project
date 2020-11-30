package ru.otus.library.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.library.domain.*;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.UserRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ChangeLog(order = "001")
public class MongoDBInit {
    @ChangeSet(order = "001", id = "clear", runAlways = true, author = "abashmakov")
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "002", id = "init", runAlways = true, author = "abashmakov")
    public void initDB(BookRepository bookRepository){
        bookRepository.insert(initBook("Famous Book", new String[] {"Talent", "Pushkin"}, new String[] {"Lyrics", "Roman"}, null));
        bookRepository.insert(initBook("Incredible Book", new String[] {"Bob", "Sam", "Joe"}, new String[] {"Fantasy", "Detective"}, null));
        bookRepository.insert(initBook("War And Peace", new String[] {"Tolstoy"}, new String[] {"Novel"}, 278d));
        bookRepository.insert(initBook("Sleepy Beauty", new String[] {"Pushkin"}, new String[] {"Poetry"}, 300d));
        bookRepository.insert(initBook("It", new String[] {"King"}, new String[] {"Thriller"}, 127d));
        bookRepository.insert(initBook("10 little niggers", new String[] {"Agatha Christie"}, new String[] {"Detective"}, 150d));
        bookRepository.insert(initBook("Quantum Physics", new String[] {"Landau"}, new String[] {"Science"}, 185d));
        bookRepository.insert(initBook("Worker And Farmer", new String[] {"Typography"}, new String[] {"Journal"}, 180d));
        bookRepository.insert(initBook("The Adventures of Tom Sawyer", new String[] {"M. Twain"}, new String[] {"Adventures"}, 185d));
    }

    @ChangeSet(order = "003", id = "userInit", runAlways = true, author = "abashmakov")
    public void initUser(UserRepository userRepository){
        userRepository.save(
                User.builder()
                        .login("admin")
                        .password("$2y$10$cVPHcu5FxHPiiRpQpjYCqOc1b8Qm/hir0kUt72AuOVPfCQYZ706yi")
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .build());
        userRepository.save(
                User.builder()
                        .login("user")
                        .password("$2y$10$60w33EB8NkhHk2ZSVPoNY.Bf0CYlZa7NYEouxXFB3exP8LcWVSCki")
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .build());
    }

    private Book initBook(String title, String[] authors, String[] genres, Double price) {
        List<Author> authorList = Arrays.stream(authors)
                .map(author -> Author.builder()
                        .id(UUID.randomUUID().toString())
                        .name(author)
                        .build()).collect(Collectors.toList());
        List<Genre> genreList = Arrays.stream(genres)
                .map(genre -> Genre.builder()
                        .id(UUID.randomUUID().toString())
                        .name(genre)
                        .build()).collect(Collectors.toList());
        return Book.builder()
                .name(title)
                .authors(authorList)
                .genres(genreList)
                .price(price)
                .build();
    }
}
