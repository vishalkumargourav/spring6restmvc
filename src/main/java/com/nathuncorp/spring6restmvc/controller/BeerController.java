package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.model.Beer;
import com.nathuncorp.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@AllArgsConstructor
@Slf4j
public class BeerController {
    private final BeerService beerService;

    public Beer getBeerById(UUID id){
        log.debug("Get Beer By ID - in controller");

        return beerService.getBeerById(id);
    }
}
