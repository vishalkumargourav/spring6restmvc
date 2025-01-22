package com.nathuncorp.spring6restmvc.repository;

import com.nathuncorp.spring6restmvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder().beerName("New Test Beer").build());

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
        assertThat(savedBeer.getBeerName()).isEqualTo("New Test Beer");
    }
}