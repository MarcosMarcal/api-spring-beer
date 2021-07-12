package com.beerhouse.tools;

import com.beerhouse.persistence.relational.entities.Beer;
import com.beerhouse.persistence.relational.repositories.implement.BeerRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.beerhouse.tools.BeerBuilder.*;

/**
 *  Essa classe é utilizada para gerar dados fictícios na base.
 *  Caso não esteja utilizando uma base volátil como a H2 embutida,
 *  comente a anotação '@Component' para o Spring não executar.
 *
 * */
@Log
@Component
public class DummyData implements CommandLineRunner {

    private final BeerRepository beerRepo;

    public DummyData(BeerRepository beerRepo) {
        this.beerRepo = beerRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Beer> beers = beerRepo.findAll();

        log.info("Limpando a tabela...");
        if(!beers.isEmpty())
            beers.forEach( beer -> beerRepo.deleteById(beer.getId()));

        var newBeers = List
                .of(
                        aBeer().named("BEER 1").costing(9.0).now(),
                        aBeer().named("BEER 2").costing(11.0).now(),
                        aRedBeer().named("BEER 3").costing(28.0).now(),
                        aRedBeer().named("BEER 4").costing(32.0).now(),
                        aBlueBeer().named("BEER 5").costing(17.0).now(),
                        aBlueBeer().named("BEER 6").costing(22.0).now()
                );

        log.info("Populando com novos dados fictícios...");
        newBeers.forEach(beerRepo::save);

    }
}
