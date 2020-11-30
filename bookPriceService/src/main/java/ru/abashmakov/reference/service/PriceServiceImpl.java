package ru.abashmakov.reference.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abashmakov.reference.domain.BookPrice;
import ru.abashmakov.reference.repository.PriceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Transactional
    @Override
    public BookPrice save(BookPrice author) {
        return priceRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookPrice> findByName(String name) {
        return priceRepository.findByBookNameLike(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookPrice> findById(Long id) {
        return priceRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookPrice> findAll() {
        List<BookPrice> authors = new ArrayList<>();
        priceRepository.findAll().forEach(authors::add);
        return authors;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        priceRepository.deleteById(id);
    }
}
