package ru.abashmakov.reference.mapper;

import org.mapstruct.Mapper;
import ru.abashmakov.reference.domain.Genre;
import ru.abashmakov.reference.dto.GenreDto;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre fromDto(GenreDto dto);
    GenreDto toDto(Genre genre);
}
