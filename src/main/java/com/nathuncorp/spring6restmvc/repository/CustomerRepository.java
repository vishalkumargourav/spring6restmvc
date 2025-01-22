package com.nathuncorp.spring6restmvc.repository;

import com.nathuncorp.spring6restmvc.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
