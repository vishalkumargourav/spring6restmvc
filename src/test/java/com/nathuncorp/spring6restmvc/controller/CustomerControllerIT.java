package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.entities.Beer;
import com.nathuncorp.spring6restmvc.entities.Customer;
import com.nathuncorp.spring6restmvc.mapper.CustomerMapper;
import com.nathuncorp.spring6restmvc.model.CustomerDTO;
import com.nathuncorp.spring6restmvc.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

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

    @Test
    @Transactional
    @Rollback
    void testDeleteCustomer() {
        Customer customer = customerRepository.findAll().get(0);

        customerController.deleteById(customer.getId());

        assertThat(customerRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback
    void testUpdateCustomerByID() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO updatedCustomer = customerMapper.customerToCustomerDTO(customer);
        updatedCustomer.setId(null);
        updatedCustomer.setVersion(null);
        updatedCustomer.setCustomerName("Update Name");

        customerController.updateCustomerById(customer.getId(), updatedCustomer);

        assertThat(customerRepository.findAll().get(0).getCustomerName()).isEqualTo("Update Name");
    }
}