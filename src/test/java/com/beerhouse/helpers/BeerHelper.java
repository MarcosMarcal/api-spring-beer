package com.beerhouse.helpers;

import com.beerhouse.model.Beer;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.beerhouse.tools.BeerBuilder.*;
import static com.beerhouse.tools.BeerBuilder.aBlueBeer;

@Getter
public class BeerHelper {

    List<Beer> beers;

    public BeerHelper() {
        this.beers = List
                .of(
                        aBeer().named("BEER 1").costing(9.0).now(),
                        aBeer().named("BEER 2").costing(11.0).now(),
                        aRedBeer().named("BEER 3").costing(28.0).now(),
                        aRedBeer().named("BEER 4").costing(32.0).now(),
                        aBlueBeer().named("BEER 5").costing(17.0).now(),
                        aBlueBeer().named("BEER 6").costing(22.0).now()
                );
    }

    public Page<Beer> getAllBeers () {
        return new PageImpl<>(beers, Pageable.unpaged(), 6);
    }

    public Beer getUpdatedBeer(Beer beer) {
        beer.setName(beer.getName()+ " UPDATED");
        beer.setIngredients(beer.getIngredients()+ " UPDATED");
        beer.setAlcoholContent(beer.getAlcoholContent()+ " UPDATED");
        beer.setCategory(beer.getCategory()+ " UPDATED");
        beer.setPrice(beer.getPrice() * 2);
        return beer;
    }

    public Beer getPartiallyUpdatedBeer(Beer beer) {
        beer.setName(beer.getName()+ " UPDATED");
        beer.setCategory(beer.getCategory()+ " UPDATED");
        return beer;
    }

}
