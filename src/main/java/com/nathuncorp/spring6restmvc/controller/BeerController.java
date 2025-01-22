package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.model.BeerDTO;
import com.nathuncorp.spring6restmvc.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BeerController {
    public final static String BEER_PATH = "/api/v1/beer";
    public final static String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @RequestMapping(value = BEER_PATH_ID, method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = BEER_PATH_ID, method = RequestMethod.PUT)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beer) {
        beerService.updateBeerById(beerId, beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH + "/" + beerId);

        return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = BEER_PATH, method = RequestMethod.POST)
    public ResponseEntity saveNewBeer(@RequestBody BeerDTO beer) {
        log.debug("Save New Beer - in controller");

        BeerDTO savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH + "/" + savedBeer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = BEER_PATH, method = RequestMethod.GET)
    public List<BeerDTO> listBeers() {
        log.debug("List Beers - in controller 1234");

        return beerService.listBeers();
    }

    @RequestMapping(value = BEER_PATH_ID, method = RequestMethod.GET)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer By ID - in controller");

        return beerService.getBeerById(beerId);
    }
}
