package ru.abashmakov.reference.mapper;

import org.mapstruct.Mapper;
import ru.abashmakov.reference.domain.BookPrice;
import ru.abashmakov.reference.dto.PriceDto;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    BookPrice fromDto(PriceDto dto);
    PriceDto toDto(BookPrice author);
}
