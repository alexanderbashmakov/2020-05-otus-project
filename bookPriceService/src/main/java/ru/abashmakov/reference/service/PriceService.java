package ru.abashmakov.reference.service;

import ru.abashmakov.reference.domain.BookPrice;

import java.util.List;
import java.util.Optional;

public interface PriceService {
    BookPrice save(BookPrice author);
    List<BookPrice> findByName(String name);
    Optional<BookPrice> findById(Long id);
    List<BookPrice> findAll();
    void deleteById(Long id);
}
