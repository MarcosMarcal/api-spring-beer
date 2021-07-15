package com.beerhouse.repository.implement;


import com.beerhouse.models.Beer;
import com.beerhouse.repository.JpaBeerRepository;
import com.beerhouse.repository.MainBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BeerRepository implements MainBeerRepository {

    private final JpaBeerRepository beerRepo;

    @Autowired
    public BeerRepository(JpaBeerRepository beerRepository) {
        this.beerRepo = beerRepository;
    }

    @Override
    public Beer save(Beer beer) {
        return this.beerRepo.saveAndFlush(beer);
    }

    @Override
    public void deleteById(Long id) {
        this.beerRepo.deleteById(id);
    }

    @Override
    public Optional<Beer> findById(Long id) {
        return this.beerRepo.findById(id);
    }

    @Override
    public Page<Beer> findAll(Pageable pageable) {
        return this.beerRepo.findAll(pageable);
    }

    @Override
    public boolean exists(Example<Beer> beer) {
        return beerRepo.exists(beer);
    }

}
