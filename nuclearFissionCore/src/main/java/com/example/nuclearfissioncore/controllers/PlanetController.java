package com.example.nuclearfissioncore.controllers;

import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PlanetController {

    private final PlanetRepository planetRepository;

    public PlanetController(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @GetMapping("/planets/{id}")
    public Planet getPlanet(@PathVariable Integer id) {
        return planetRepository.findById(id).orElse(null);
    }

    @GetMapping("/planets")
    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }
}
