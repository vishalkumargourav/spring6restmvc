package com.nathuncorp.spring6restmvc.service;

import com.nathuncorp.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getCustomers();

    Customer getCustomerById(UUID customerId);

    Customer saveNewCustomer(Customer newCustomer);

    void updateCustomerById(UUID customerId, Customer customer);
}
