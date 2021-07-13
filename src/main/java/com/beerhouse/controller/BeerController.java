package com.beerhouse.controller;

import com.beerhouse.service.implement.BeerService;
import com.beerhouse.model.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class BeerController {

    private final BeerService beerService;

    // TODO: 13/07/2021 - Implementar métodos do controller...

    @GetMapping("/beers")
    public Page<Beer> findAllBeers(Pageable pageable) {
        return null;
    }

    @PostMapping("/beers")
    public Beer createBeer(@RequestBody Beer beer) {
        return null;
    }

    @GetMapping("/beers/{id}")
    public Beer findBeerById(@PathVariable("id") Long id) {
        return null;
    }

    @PutMapping("/beers/{id}")
    public Beer updateBeer(@PathVariable("id") Long id) {
        return null;
    }

    @PatchMapping("/beers/{id}")
    public Beer changeBeer(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping("/beers/{id}")
    public Beer deleteBeer(@PathVariable("id") Long id) {
        return null;
    }

}
