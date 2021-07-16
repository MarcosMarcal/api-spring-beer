package com.beerhouse.controller.response;

import com.beerhouse.models.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BeerResponse extends ResponseEntity<Beer> {

    public BeerResponse(HttpStatus status) {
        super(status);
    }

    public BeerResponse(Beer body, HttpStatus status) {
        super(body, status);
    }

}
