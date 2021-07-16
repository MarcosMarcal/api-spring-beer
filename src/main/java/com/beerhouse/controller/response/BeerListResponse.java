package com.beerhouse.controller.response;

import com.beerhouse.models.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BeerListResponse extends ResponseEntity<List<Beer>> {

    public BeerListResponse(HttpStatus status) {
        super(status);
    }

    public BeerListResponse(List<Beer> body, HttpStatus status) {
        super(body, status);
    }

}
