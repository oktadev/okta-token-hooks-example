package com.okta.examples.apiamhooks.controller;

import com.okta.examples.apiamhooks.model.Beer;
import com.okta.examples.apiamhooks.repository.BeerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
class BeerController {

    private BeerRepository repository;

    public BeerController(BeerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/good-beers")
    public Collection<Beer> goodBeers() {

        return repository.findAll().stream()
            .filter(this::isGreat)
            .collect(Collectors.toList());
    }

    @GetMapping("/api/beers")
    public Collection<Beer> beers() {
        return repository.findAll();
    }

    private boolean isGreat(Beer beer) {
        return !beer.getName().equals("Budweiser") &&
            !beer.getName().equals("Coors Light") &&
            !beer.getName().equals("PBR");
    }
}