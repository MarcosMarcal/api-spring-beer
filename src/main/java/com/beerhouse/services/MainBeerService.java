package com.beerhouse.services;

import com.beerhouse.exceptions.BeerException;
import com.beerhouse.model.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MainBeerService {

    Page<Beer> findAll(Pageable pageable);
    Beer createBeer(Beer beer) throws BeerException;
    Optional<Beer> findById(Long id);
    Beer updateBeer(Beer beer) throws BeerException;
    Beer updatePartiallyBeer(Beer beer) throws BeerException;
    Beer deleteBeer(Long id);

}
