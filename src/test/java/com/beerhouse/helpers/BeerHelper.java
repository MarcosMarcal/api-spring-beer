package com.beerhouse.helpers;

import com.beerhouse.models.Beer;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

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

    public Beer getUpdatedBeer() {
        var beer = aBeer().named("BEER 6 UPDATED").costing(44.0).now();
        beer.setIngredients("BLUE INGREDIENTS UPDATED");
        beer.setAlcoholContent("10 ALCOHOL UPDATED");
        beer.setCategory("BLUE OCEAN UPDATED");
        return beer;
    }

    public Beer getPartiallyUpdatedBeer() {
        var beer = aBlueBeer().named("BEER 5 UPDATED").costing(17.0).now();
        beer.setCategory("BLUE OCEAN UPDATED");
        return beer;
    }

    public Map<String, Object> getPartiallyUpdatedBeerBody() {

        return Map.of("name", "BEER 5 UPDATED", "category", "BLUE OCEAN UPDATED");
    }

}
