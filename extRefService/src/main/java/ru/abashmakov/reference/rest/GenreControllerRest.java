package ru.abashmakov.reference.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.abashmakov.reference.domain.Genre;
import ru.abashmakov.reference.dto.GenreDto;
import ru.abashmakov.reference.mapper.GenreMapper;
import ru.abashmakov.reference.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GenreControllerRest {
    private final GenreMapper mapper;
    private final GenreService service;

    @GetMapping(value = "/api/genres")
    public List<GenreDto> genreList() {
        return service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/genres/{name}")
    public List<GenreDto> genreListByName(@PathVariable String name) {
        List<Genre> genres = service.findByName("%" + name + "%");
        return genres.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/genre/{id}")
    public GenreDto getGenre(@PathVariable Long id) {
        return service.findById(id).map(mapper::toDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/api/genre")
    public ResponseEntity<GenreDto> genreSave(@RequestBody GenreDto genreDto) {
        Genre savedGenre = service.save(mapper.fromDto(genreDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedGenre));
    }
    
    @PutMapping(value = "/api/genre/{id}")
    public ResponseEntity<GenreDto> genreSave(@PathVariable Long id, @RequestBody GenreDto genreDto) {
        Genre savedGenre = service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        savedGenre.setName(genreDto.getName());
        savedGenre.setDescription(genreDto.getDescription());
        service.save(savedGenre);
        return ResponseEntity.ok(mapper.toDto(savedGenre));
    }

    @DeleteMapping(value = "/api/genre/{id}")
    public ResponseEntity<Void> genreDelete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

/*
    @PostMapping(value = "/apiA/genre/{bookId}")
    public ResponseEntity<Genre> genreSave(@PathVariable String bookId, @RequestBody Genre genre) {
        service.create(bookId, Genre.toDomainObject(genre));
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @PutMapping(value = "/api/genre/{id}")
    public ResponseEntity<Genre> genreUpdate(@PathVariable String id, @RequestBody Genre genre){
        service.update(id, Genre.toDomainObject(genre));
        return ResponseEntity.ok(genre);
    }

    @DeleteMapping(value = "/api/genre/{id}")
    public ResponseEntity<Void> genreDelete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
*/
}
