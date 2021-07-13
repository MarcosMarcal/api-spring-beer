package com.beerhouse.service.implement;

import com.beerhouse.service.MainBeerService;
import com.beerhouse.model.Beer;
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
