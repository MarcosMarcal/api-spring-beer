package com.beerhouse.domain;

import com.beerhouse.persistence.relational.entities.Beer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MainBeerService {

    Page<Beer> listBeersPage();
    List<Beer> listBeers();

}
