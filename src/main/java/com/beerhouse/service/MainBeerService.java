package com.beerhouse.service;

import com.beerhouse.model.Beer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MainBeerService {

    Page<Beer> listBeersPage();
    List<Beer> listBeers();

}
