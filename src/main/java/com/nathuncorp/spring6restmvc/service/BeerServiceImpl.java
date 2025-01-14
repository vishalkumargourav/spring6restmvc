package com.nathuncorp.spring6restmvc.service;

import com.nathuncorp.spring6restmvc.model.Beer;
import com.nathuncorp.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer By ID - in service");

        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PILSNER)
                .upc("123456")
                .price(new BigDecimal("12.34"))
                .quantityOnHand(234)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
