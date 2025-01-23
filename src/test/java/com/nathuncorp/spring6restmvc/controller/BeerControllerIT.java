package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.entities.Beer;
import com.nathuncorp.spring6restmvc.model.BeerDTO;
import com.nathuncorp.spring6restmvc.repository.BeerRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController beerController;
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testBeerByIdNotFoundException() {
        beerController.getBeerById(UUID.randomUUID());
    }

    @Test
    void testListAllBeer() {
        List<BeerDTO> beers = beerController.listBeers();

        assertThat(beers.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyBeerList() {
        beerRepository.deleteAll();
        List<BeerDTO> beers = beerController.listBeers();

        assertThat(beers.size()).isEqualTo(0);
    }

    @Test
    void getBeerById() {
        Beer beer = beerRepository.findAll().get(0);

        BeerDTO beerDTO = beerController.getBeerById(beer.getId());

        assertThat(beer.getId()).isEqualTo(beerDTO.getId());
    }
}