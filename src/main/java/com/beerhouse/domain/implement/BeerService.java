package com.beerhouse.domain.implement;

import com.beerhouse.domain.MainBeerService;
import com.beerhouse.persistence.relational.entities.Beer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService implements MainBeerService {

    @Override
    public Page<Beer> listBeersPage() {
        return null;
    }

    @Override
    public List<Beer> listBeers() {
        return null;
    }
}
