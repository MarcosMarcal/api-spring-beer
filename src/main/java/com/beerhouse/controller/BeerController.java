package com.beerhouse.controller;

import com.beerhouse.controller.response.BeerListResponse;
import com.beerhouse.controller.response.BeerResponse;
import com.beerhouse.exceptions.BadRequestException;
import com.beerhouse.exceptions.BeerException;
import com.beerhouse.services.BeerService;
import com.beerhouse.services.implement.BeerServiceImpl;
import com.beerhouse.models.Beer;
import lombok.RequiredArgsConstructor;
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
    public BeerListResponse findAllBeers(Pageable pageable) {
        return new BeerListResponse(beerService.findAll(pageable).getContent(), HttpStatus.OK);
    }

    @PostMapping("/beers")
    @ResponseStatus(HttpStatus.CREATED)
    public BeerResponse createBeer(@RequestBody Beer beer) throws BeerException {
        return new BeerResponse(beerService.createBeer(beer), HttpStatus.CREATED);
    }

    @GetMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BeerResponse findBeerById(@PathVariable("id") Long id) throws BeerException {
        return new BeerResponse(beerService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BeerResponse updateBeer(@RequestBody Beer beer, @PathVariable("id") Long id) throws BeerException, BadRequestException {
        return new BeerResponse(beerService.updateBeer(beer, id), HttpStatus.OK);
    }

    @PatchMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BeerResponse updatePartiallyBeer(@RequestBody Map<String, Object> body, @PathVariable("id") Long id) throws BeerException, BadRequestException {
        return new BeerResponse(beerService.updatePartiallyBeer(body, id), HttpStatus.OK);
    }

    @DeleteMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteBeer(@PathVariable("id") Long id) throws BeerException {
                beerService.deleteBeer(id);
        return ResponseEntity.of(Optional.of("Beer deleted!"));
    }

}
