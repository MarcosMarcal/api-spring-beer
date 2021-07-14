package com.beerhouse.services.implement;

import com.beerhouse.exceptions.BeerException;
import com.beerhouse.repository.implement.BeerRepository;
import com.beerhouse.services.MainBeerService;
import com.beerhouse.model.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeerService implements MainBeerService {

    private final BeerRepository beerRepository;

    // TODO: 13/07/2021 - Implementar métodos do service...

    @Override
    public Page<Beer> findAll(Pageable pageable) {
        return beerRepository.findAll(pageable);
    }

    @Override
    public Beer createBeer(Beer beer) throws BeerException {

        if(beerRepository.exists(Example.of(beer)))
            throw new BeerException("This beer already exists.");

        return beerRepository.save(beer);
    }

    @Override
    public Optional<Beer> findById(Long id) {
        return beerRepository.findById(id);
    }

    @Override
    public Beer updateBeer(Beer beer) throws BeerException {
        return null;
    }

    @Override
    public Beer updatePartiallyBeer(Beer beer) throws BeerException{
        return null;
    }

    // TODO: 14/07/2021 - Método de deletar deveria ter retorno ? ...
    @Override
    public Beer deleteBeer(Long id) {
        return null;
    }
}
