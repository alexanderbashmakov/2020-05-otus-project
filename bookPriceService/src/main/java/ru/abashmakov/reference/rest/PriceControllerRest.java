package ru.abashmakov.reference.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.abashmakov.reference.domain.BookPrice;
import ru.abashmakov.reference.dto.PriceDto;
import ru.abashmakov.reference.mapper.PriceMapper;
import ru.abashmakov.reference.service.PriceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PriceControllerRest {

    private final PriceMapper mapper;
    private final PriceService service;

    @RequestMapping(method = RequestMethod.GET, value = "/api/prices")
    public List<PriceDto> priceList() {
        return service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/price/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PriceDto> priceListByBookName(@PathVariable String name) {
        List<BookPrice> prices = service.findByName("%" + name + "%");
        return prices.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/price")
    public ResponseEntity<PriceDto> priceSave(@RequestBody PriceDto priceDto) {
        BookPrice savedPrice = service.save(mapper.fromDto(priceDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedPrice));
    }

    @PutMapping(value = "/api/price/{id}")
    public ResponseEntity<PriceDto> priceSave(@PathVariable Long id, @RequestBody PriceDto priceDto) {
        BookPrice bookPrice = service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        bookPrice.setBookName(priceDto.getBookName());
        bookPrice.setPrice(priceDto.getPrice());
        service.save(bookPrice);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(bookPrice));
    }

    @DeleteMapping(value = "/api/price/{id}")
    public ResponseEntity<Void> priceDelete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
