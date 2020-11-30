package ru.abashmakov.reference.service;

import ru.abashmakov.reference.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author save(Author author);
    List<Author> findByName(String name);
    Optional<Author> findById(Long id);
    List<Author> findAll();
    void deleteById(Long id);
}
