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

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerService implements MainBeerService {

    private final BeerRepository beerRepository;

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
    public Beer updateBeer(Beer beer, Long id) throws BeerException {

        Optional<Beer> updatedBeer = beerRepository.findById(id);

        if(!updatedBeer.isPresent())
            throw new BeerException("The informed beer does not exist to update.");

        updatedBeer.get().setName(beer.getName());
        updatedBeer.get().setIngredients(beer.getIngredients());
        updatedBeer.get().setAlcoholContent(beer.getAlcoholContent());
        updatedBeer.get().setPrice(beer.getPrice());
        updatedBeer.get().setCategory(beer.getCategory());

        return beerRepository.save(updatedBeer.get());
    }

    @Override
    public Beer updatePartiallyBeer(Map<String, Object> body, Long id) throws BeerException {

        Optional<Beer> beer = beerRepository.findById(id);

        if(!beer.isPresent())
            throw new BeerException("The informed beer does not exist to update.");

        body.forEach((key, value) -> { switch (key) {
            case "name" : beer.get().setName(value.toString()); break;
            case "ingredients" : beer.get().setIngredients(value.toString()); break;
            case "alcoholContent" : beer.get().setAlcoholContent(value.toString()); break;
            case "price" : beer.get().setPrice(Double.parseDouble(value.toString())); break;
            case "category" : beer.get().setCategory(value.toString()); break;
            default: break;
        } } );

        return beerRepository.save(beer.get());
    }

    @Override
    public void deleteBeer(Long id) throws BeerException {

        if(!beerRepository.findById(id).isPresent())
            throw new BeerException("The informed beer does not exist to delete.");

        beerRepository.deleteById(id);
    }
}
