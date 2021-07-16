package com.beerhouse.services;

import com.beerhouse.exceptions.BadRequestException;
import com.beerhouse.exceptions.BeerException;
import com.beerhouse.models.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BeerService {

    Page<Beer> findAll(Pageable pageable);
    Beer createBeer(Beer beer) throws BeerException;
    Beer findById(Long id) throws BeerException;
    Beer updateBeer(Beer beer, Long id) throws BeerException, BadRequestException;
    Beer updatePartiallyBeer(Map<String, Object> body, Long id) throws BeerException, BadRequestException;
    void deleteBeer(Long id) throws BeerException;

}
