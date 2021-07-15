package com.beerhouse.repository;

import com.beerhouse.models.Beer;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MainBeerRepository {

    Beer save(Beer beer);

    Page<Beer> findAll(Pageable pageable);

    void deleteById(Long id);

    Optional<Beer> findById(Long id);

    boolean exists(Example<Beer> beer);

}
