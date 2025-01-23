package com.nathuncorp.spring6restmvc.service;

import com.nathuncorp.spring6restmvc.mapper.CustomerMapper;
import com.nathuncorp.spring6restmvc.model.CustomerDTO;
import com.nathuncorp.spring6restmvc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(UUID customerId) {
        return customerMapper.customerToCustomerDTO(customerRepository.findById(customerId).orElse(null));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO newCustomer) {
        return null;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {

    }

    @Override
    public void deleteById(UUID customerId) {

    }
}
