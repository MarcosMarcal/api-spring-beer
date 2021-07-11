package com.beerhouse.persistence.relational.repositories;

import com.beerhouse.persistence.relational.entities.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MainBeerRepository {

    Beer save(Beer beer);

    List<Beer> findAll();

    void deleteById(Long id);

    Optional<Beer> findById(Long id);

    Page<Beer> findAllPage(Pageable pageable);

    //Beer create();

}
