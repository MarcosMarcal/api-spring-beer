package com.beerhouse.controller;

import com.beerhouse.service.implement.BeerService;
import com.beerhouse.model.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/beers")
public class BeerController {

    private final BeerService beerService;

    @GetMapping()
    public Page<Beer> listBeersPage(Pageable pageable) {
        return null;
    }
}
