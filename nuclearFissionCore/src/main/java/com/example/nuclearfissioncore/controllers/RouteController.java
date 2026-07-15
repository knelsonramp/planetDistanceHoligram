package com.example.nuclearfissioncore.controllers;

import com.example.nuclearfissioncore.dto.PathAndDistanceDto;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import com.example.nuclearfissioncore.services.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteController {

    private final RouteRepository routeRepository;
    private final RouteService routeService;

    public RouteController(RouteRepository routeRepository, RouteService routeService) {
        this.routeRepository = routeRepository;
        this.routeService = routeService;
    }

    @GetMapping("/routes/{id}")
    public Route getPlanet(@PathVariable Integer id) {
        return routeRepository.findById(id).orElse(null);
    }

    @GetMapping("/routes")
    public List<Route> getAllPlanets() {
        return routeRepository.findAll();
    }

    @GetMapping("/shortestPathBetweenPlanets")
    public PathAndDistanceDto getDistanceBetweenPlanets(@RequestParam Integer originPlanetId, @RequestParam Integer destinationPlanetId) {
        return routeService.findShortestPath(originPlanetId, destinationPlanetId);
    }
}
