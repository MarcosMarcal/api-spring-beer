package com.beerhouse.services;

import com.beerhouse.exceptions.BeerException;
import com.beerhouse.model.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface MainBeerService {

    Page<Beer> findAll(Pageable pageable);
    Beer createBeer(Beer beer) throws BeerException;
    Optional<Beer> findById(Long id);
    Beer updateBeer(Beer beer, Long id) throws BeerException;
    Beer updatePartiallyBeer(Map<String, Object> body, Long id) throws BeerException;
    void deleteBeer(Long id) throws BeerException;

}
