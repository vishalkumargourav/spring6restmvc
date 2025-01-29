package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.entities.Beer;
import com.nathuncorp.spring6restmvc.mapper.BeerMapper;
import com.nathuncorp.spring6restmvc.model.BeerDTO;
import com.nathuncorp.spring6restmvc.model.BeerStyle;
import com.nathuncorp.spring6restmvc.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController beerController;
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    BeerMapper beerMapper;

    @Test
    void testUpdateBeerById() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String updateName = "New Beer Name";
        beerDTO.setBeerName(updateName);

        ResponseEntity responseEntity = beerController.updateById(beer.getId(), beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer updateBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updateBeer.getBeerName()).isEqualTo(updateName);
    }

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

    @Transactional
    @Rollback
    @Test
    void saveNewBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerStyle(BeerStyle.PILSNER)
                .beerName("New Beer Saved")
                .price(BigDecimal.ONE)
                .upc("34543")
                .quantityOnHand(123)
                .build();

        ResponseEntity responseEntity = beerController.saveNewBeer(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID uuid = UUID.fromString(locationUUID[4]);

        Beer beer = beerRepository.findById(uuid).get();
        assertThat(beer).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    void deleteBeerById() {
        Beer beer = beerRepository.findAll().get(0);

        ResponseEntity responseEntity = beerController.deleteById(beer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(beerRepository.findAll().size()).isEqualTo(2);
    }
}