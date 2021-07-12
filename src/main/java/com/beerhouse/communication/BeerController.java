package com.beerhouse.communication;

import com.beerhouse.domain.implement.BeerService;
import com.beerhouse.persistence.relational.entities.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/beers")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping()
    public Page<Beer> listBeersPage(Pageable pageable) {
        return null;
    }
}
