package com.nathuncorp.spring6restmvc.service;

import com.nathuncorp.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
}
