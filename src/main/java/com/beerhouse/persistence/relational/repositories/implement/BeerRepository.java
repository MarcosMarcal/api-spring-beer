package com.beerhouse.persistence.relational.repositories.implement;


import com.beerhouse.persistence.relational.entities.Beer;
import com.beerhouse.persistence.relational.repositories.JpaBeerRepository;
import com.beerhouse.persistence.relational.repositories.MainBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<Beer> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Beer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Beer> findAllPage(Pageable pageable) {
        return null;
    }
}
