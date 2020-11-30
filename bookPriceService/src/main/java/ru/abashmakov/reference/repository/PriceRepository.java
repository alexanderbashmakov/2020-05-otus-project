package ru.abashmakov.reference.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.abashmakov.reference.domain.BookPrice;

import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<BookPrice, Long> {
    List<BookPrice> findByBookNameLike(String name);
}
