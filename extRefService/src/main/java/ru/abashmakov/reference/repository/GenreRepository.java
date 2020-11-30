package ru.abashmakov.reference.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.abashmakov.reference.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    List<Genre> findByNameLike(String name);
}
