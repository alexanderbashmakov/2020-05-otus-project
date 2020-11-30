package ru.abashmakov.reference.mapper;

import org.mapstruct.Mapper;
import ru.abashmakov.reference.domain.Author;
import ru.abashmakov.reference.dto.AuthorDto;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author fromDto(AuthorDto dto);
    AuthorDto toDto(Author author);
}
