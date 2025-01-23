package com.nathuncorp.spring6restmvc.mapper;

import com.nathuncorp.spring6restmvc.entities.Beer;
import com.nathuncorp.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDTOtoBeer(BeerDTO dto);

    BeerDTO beerToBeerDTO(Beer beer);
}
