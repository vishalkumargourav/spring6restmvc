package com.nathuncorp.spring6restmvc.service;

import com.nathuncorp.spring6restmvc.entities.Beer;
import com.nathuncorp.spring6restmvc.mapper.BeerMapper;
import com.nathuncorp.spring6restmvc.model.BeerDTO;
import com.nathuncorp.spring6restmvc.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BeerDTO getBeerById(UUID id) {
        return beerMapper.beerToBeerDTO(beerRepository.findById(id).orElse(null));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        Beer beerSaved = beerRepository.save(beerMapper.beerDTOtoBeer(beer));
        return beerMapper.beerToBeerDTO(beerSaved);
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beer) {
        beerRepository.findById(beerId).ifPresent(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            beerRepository.save(foundBeer);
        });
    }

    @Override
    public void deleteById(UUID beerId) {

    }
}
