package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.model.Beer;
import com.nathuncorp.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @RequestMapping(value = "{beerId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.PUT)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){
        beerService.updateBeerById(beerId, beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + beerId);

        return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveNewBeer(@RequestBody Beer beer){
        log.debug("Save New Beer - in controller");

        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> listBeers(){
        log.debug("List Beers - in controller 1234");

        return beerService.listBeers();
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){
        log.debug("Get Beer By ID - in controller");

        return beerService.getBeerById(beerId);
    }
}
