package com.example.nuclearfissioncore.controllers;

import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final PlanetRepository planetRepository;

    public HelloController(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @GetMapping("/planets/{id}")
    public Planet getPlanet(@PathVariable Integer id) {
        return new Planet("A");
//        return planetRepository.findById(id).orElse(null);
    }

    @GetMapping("/hello")
    public String hello() {
        return "nuclear fuzzbuzz";
    }
}
