package ru.otus.library.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.otus.library.dto.PriceDto;

import java.util.List;

@FeignClient(name = "book-price-service")
public interface ExternalServiceProxy {

    @RequestMapping(value = "/api/prices", method = RequestMethod.GET)
    List<PriceDto> priceList();

    @RequestMapping(value = "/api/price/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PriceDto> priceListByBookName(@PathVariable String name);
}
