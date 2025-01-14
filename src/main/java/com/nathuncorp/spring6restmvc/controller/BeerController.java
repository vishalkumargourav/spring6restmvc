package com.nathuncorp.spring6restmvc.controller;

import com.nathuncorp.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class BeerController {
    private final BeerService beerService;
}
