package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.model.BeerDTO;
import com.nathuncorp.spring6restmvc.model.CustomerDTO;
import com.nathuncorp.spring6restmvc.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Rollback
    @Test
    void testZeroCustomerList() {
        customerRepository.deleteAll();
        List<CustomerDTO> customerDTOs = customerController.getAllCustomers();

        assertThat(customerDTOs.size()).isEqualTo(0);
    }

    @Test
    void testListCustomer() {
        List<CustomerDTO> customerDTOs = customerController.getAllCustomers();

        assertThat(customerDTOs.size()).isEqualTo(3);
    }
}