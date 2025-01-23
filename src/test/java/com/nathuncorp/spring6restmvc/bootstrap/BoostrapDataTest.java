package com.nathuncorp.spring6restmvc.bootstrap;

import com.nathuncorp.spring6restmvc.repository.BeerRepository;
import com.nathuncorp.spring6restmvc.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BoostrapDataTest {
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    CustomerRepository customerRepository;

    BoostrapData boostrapData;

    @BeforeEach
    void setUp() {
        boostrapData = new BoostrapData(beerRepository, customerRepository);
    }

    @Test
    void testRun() throws Exception {
        boostrapData.run();

        assertThat(beerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}