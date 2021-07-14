package com.beerhouse.services;

import com.beerhouse.model.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MainBeerService {

    Page<Beer> findAll(Pageable pageable);
    Beer create(Beer beer);
    Optional<Beer> findById(Long id);
    Beer update(Long id);
    Beer updatePartial(Long id);
    Beer delete(Long id);

}
