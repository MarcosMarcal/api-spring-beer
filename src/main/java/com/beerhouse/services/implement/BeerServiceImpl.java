package com.beerhouse.services.implement;

import com.beerhouse.exceptions.BadRequestException;
import com.beerhouse.exceptions.BeerException;
import com.beerhouse.repository.implement.BeerRepository;
import com.beerhouse.services.BeerService;
import com.beerhouse.models.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.beerhouse.exceptions.BadRequestException.*;
import static com.beerhouse.exceptions.BeerException.ALREADY_EXISTS;
import static com.beerhouse.exceptions.BeerException.NOT_EXISTS;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    @Override
    public Page<Beer> findAll(Pageable pageable) {
        return beerRepository.findAll(pageable);
    }

    @Override
    public Beer createBeer(Beer beer) throws BeerException {

        if(beerRepository.exists(Example.of(beer)))
            throw new BeerException(ALREADY_EXISTS);

        return beerRepository.save(beer);
    }

    @Override
    public Beer findById(Long id) throws BeerException {

        Optional<Beer> beer = beerRepository.findById(id);

        if(!beer.isPresent())
            throw new BeerException("The informed beer does not exist.");

        return beer.get();
    }

    @Override
    public Beer updateBeer(Beer beer, Long id) throws BeerException, BadRequestException {

        if(beer.hasNull())
            throw new BadRequestException(FIELDS_NOT_SENT);

        if(isIrregularPriceValue(beer.getPrice().toString()))
            throw new BadRequestException(IRREGULAR_PRICE);

        Optional<Beer> updatedBeer = beerRepository.findById(id);

        if(!updatedBeer.isPresent())
            throw new BeerException(NOT_EXISTS);

        updatedBeer.get().setName(beer.getName());
        updatedBeer.get().setIngredients(beer.getIngredients());
        updatedBeer.get().setAlcoholContent(beer.getAlcoholContent());
        updatedBeer.get().setPrice(beer.getPrice());
        updatedBeer.get().setCategory(beer.getCategory());

        return beerRepository.save(updatedBeer.get());
    }

    @Override
    public Beer updatePartiallyBeer(Map<String, Object> body, Long id) throws BeerException, BadRequestException {

        var nullValueBadRequest = new AtomicBoolean(false);
        var irregularPriceBadRequest = new AtomicBoolean(false);

        body.forEach((key, value) -> {
            if (null == value) nullValueBadRequest.set(true);
            else if (key.equals("price") && isIrregularPriceValue(value.toString())) irregularPriceBadRequest.set(true);
        } );

        if(nullValueBadRequest.get())
            throw new BadRequestException(NULL_VALUE);

        if(irregularPriceBadRequest.get())
            throw new BadRequestException(IRREGULAR_PRICE);

        Optional<Beer> beer = beerRepository.findById(id);

        if(!beer.isPresent())
            throw new BeerException(NOT_EXISTS);

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
            throw new BeerException(NOT_EXISTS);

        beerRepository.deleteById(id);
    }

    public static boolean isIrregularPriceValue(String priceStr) {
        if (priceStr == null) {
            return true;
        }
        try {
            double d = Double.parseDouble(priceStr);
        } catch (NumberFormatException nfe) {
            return true;
        }
        double d = Double.parseDouble(priceStr);
        return d < 0.0;
    }
}
