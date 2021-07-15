package com.beerhouse.controller;

import com.beerhouse.exceptions.BeerException;
import com.beerhouse.services.implement.BeerService;
import com.beerhouse.models.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/beers")
    @ResponseStatus(HttpStatus.OK)
    public Page<Beer> findAllBeers(Pageable pageable) {
        return beerService.findAll(pageable);
    }

    @PostMapping("/beers")
    @ResponseStatus(HttpStatus.CREATED)
    public Beer createBeer(@RequestBody Beer beer) throws BeerException {
        return beerService.createBeer(beer);
    }

    @GetMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Beer findBeerById(@PathVariable("id") Long id) throws BeerException {
        return beerService.findById(id);
    }

    @PutMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Beer updateBeer(@RequestBody Beer beer, @PathVariable("id") Long id) throws BeerException {
        return beerService.updateBeer(beer, id);
    }

    @PatchMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Beer updatePartiallyBeer(@RequestBody Map<String, Object> body, @PathVariable("id") Long id) throws BeerException {
        return beerService.updatePartiallyBeer(body, id);
    }

    @DeleteMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteBeer(@PathVariable("id") Long id) throws BeerException {
                beerService.deleteBeer(id);
        return ResponseEntity.of(Optional.of("Beer deleted!"));
    }

}
