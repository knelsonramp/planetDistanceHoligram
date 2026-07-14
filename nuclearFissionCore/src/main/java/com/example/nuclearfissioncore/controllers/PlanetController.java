package com.example.nuclearfissioncore.controllers;

import com.example.nuclearfissioncore.dto.PlanetWithRoutesDto;
import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import com.example.nuclearfissioncore.services.PlanetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PlanetController {

    private final PlanetRepository planetRepository;
    private final PlanetService planetService;

    public PlanetController(PlanetRepository planetRepository, PlanetService planetService) {
        this.planetRepository = planetRepository;
        this.planetService = planetService;
    }

    @GetMapping("/planets/{id}")
    public Planet getPlanet(@PathVariable Integer id) {
        return planetRepository.findById(id).orElse(null);
    }

    @GetMapping("/planets")
    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    @GetMapping("/planetsWithRoutes")
    public List<PlanetWithRoutesDto> getPlanetsWithRoutes() {
        return planetService.getPlanetWithRoutes();
    }
}
