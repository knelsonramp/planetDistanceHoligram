package com.example.nuclearfissioncore.controllers;

import com.example.nuclearfissioncore.dto.PlanetWithRoutesDto;
import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PlanetController {

    private final PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/planets/{id}")
    public Planet getPlanet(@PathVariable Integer id) {
        return planetService.getPlanet(id);
    }

    @GetMapping("/planets")
    public List<Planet> getAllPlanets() {
        return planetService.getAllPlanets();
    }

    @PostMapping("/planets")
    @ResponseStatus(HttpStatus.CREATED)
    public Planet createPlanet(@RequestBody Planet planet) {
        return planetService.createPlanet(planet);
    }

    @PutMapping("/planets/{id}")
    public Planet updatePlanet(@PathVariable Integer id, @RequestBody Planet planet) {
        return planetService.updatePlanet(id, planet);
    }

    @DeleteMapping("/planets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlanet(@PathVariable Integer id) {
        planetService.deletePlanet(id);
    }

    @GetMapping("/planetsWithRoutes")
    public List<PlanetWithRoutesDto> getPlanetsWithRoutes() {
        return planetService.getPlanetsWithRoutes();
    }
}
