package com.beerhouse.controller;

import com.beerhouse.services.implement.BeerService;
import com.beerhouse.model.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class BeerController {

    private final BeerService beerService;

    // TODO: 13/07/2021 - Implementar métodos do controller...

    @GetMapping("/beers")
    @ResponseStatus(HttpStatus.OK)
    public Page<Beer> findAllBeers(Pageable pageable) {
        return beerService.findAll(pageable);
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
