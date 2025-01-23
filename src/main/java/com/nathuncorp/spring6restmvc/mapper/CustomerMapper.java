package com.nathuncorp.spring6restmvc.mapper;

import com.nathuncorp.spring6restmvc.entities.Customer;
import com.nathuncorp.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDTOtoCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
