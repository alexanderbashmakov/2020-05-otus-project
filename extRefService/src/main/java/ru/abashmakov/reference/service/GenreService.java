package ru.abashmakov.reference.service;

import ru.abashmakov.reference.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre save(Genre genre);
    List<Genre> findByName(String name);
    List<Genre> findAll();
    Optional<Genre> findById(Long id);
    void deleteById(Long id);
}
