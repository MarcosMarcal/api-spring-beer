package com.beerhouse.services;

import com.beerhouse.exceptions.BeerException;
import com.beerhouse.models.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface MainBeerService {

    Page<Beer> findAll(Pageable pageable);
    Beer createBeer(Beer beer) throws BeerException;
    Beer findById(Long id) throws BeerException;
    Beer updateBeer(Beer beer, Long id) throws BeerException;
    Beer updatePartiallyBeer(Map<String, Object> body, Long id) throws BeerException;
    void deleteBeer(Long id) throws BeerException;

}
