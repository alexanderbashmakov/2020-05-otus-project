package ru.otus.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.abashmakov.reference.service.AuthorServiceImpl;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс AuthorServiceImpl:")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {
/*
    @Autowired
    private AuthorService service;

    @MockBean
    private AuthorRepository repository;

    @MockBean
    private IOService ioService;

    @MockBean
    private MessageBundleService messageBundleService;

    @DisplayName("сохраняет автора")
    @Test
    void saveCreate() {
        Author author = Author.builder().name("testAuthor").build();
        service.save(author);
        verify(repository, times(1)).save(author);
    }

    @DisplayName("обновляет автора")
    @Test
    void saveUpdate() {
        Author author = Author.builder().id(10L).name("testGenre").build();
        service.save(author);
        verify(repository, times(1)).save(author);
    }

    @DisplayName("выводит все записи")
    @Test
    void printAll() {
        Mockito.when(messageBundleService.getMessage("author.id")).thenReturn("author_id");
        Mockito.when(messageBundleService.getMessage("author.name")).thenReturn("author_name");

        List<Author> authors = List.of(Author.builder().id(1L).name("author").build());
        Mockito.when(repository.findAll()).thenReturn(authors);
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow(messageBundleService.getMessage("author.id"), messageBundleService.getMessage("author.name"));
        authors.forEach(author -> {
            table.addRule();
            table.addRow(author.getId(), author.getName());
        });
        table.addRule();

        service.printAll();
        verify(ioService).print(table.render());
    }

    @DisplayName("удаляет запись по id")
    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }*/
}