package ru.abashmakov.reference.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.abashmakov.reference.domain.Author;
import ru.abashmakov.reference.dto.AuthorDto;
import ru.abashmakov.reference.mapper.AuthorMapper;
import ru.abashmakov.reference.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorControllerRest {

    private final AuthorMapper mapper;
    private final AuthorService service;

    @GetMapping(value = "/api/authors")
    public List<AuthorDto> authorList() {
        return service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/authors/{name}")
    public List<AuthorDto> authorListByName(@PathVariable String name) {
        List<Author> authors = service.findByName("%" + name + "%");
        return authors.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/author/{id}")
    public AuthorDto getAuthor(@PathVariable Long id) {
        return service.findById(id).map(mapper::toDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/api/author")
    public ResponseEntity<AuthorDto> authorSave(@RequestBody AuthorDto authorDto) {
        Author savedAuthor = service.save(mapper.fromDto(authorDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedAuthor));
    }

    @PutMapping(value = "/api/author/{id}")
    public ResponseEntity<AuthorDto> authorSave(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        Author savedAuthor = service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        savedAuthor.setName(authorDto.getName());
        savedAuthor.setBirthDate(authorDto.getBirthDate());
        service.save(savedAuthor);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(savedAuthor));
    }

    @DeleteMapping(value = "/api/author/{id}")
    public ResponseEntity<Void> authorDelete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
