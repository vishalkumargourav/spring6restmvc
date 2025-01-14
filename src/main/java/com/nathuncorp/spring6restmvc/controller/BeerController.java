package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.model.Beer;
import com.nathuncorp.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class BeerController {
    private final BeerService beerService;

    @RequestMapping("/api/v1/beer")
    public List<Beer> listBeers(){
        log.debug("List Beers - in controller");

        return beerService.listBeers();
    }

    public Beer getBeerById(UUID id){
        log.debug("Get Beer By ID - in controller");

        return beerService.getBeerById(id);
    }
}
