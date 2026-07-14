package com.example.nuclearfissioncore.controllers;

import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteController {

    private final RouteRepository routeRepository;

    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @GetMapping("/routes/{id}")
    public Route getPlanet(@PathVariable Integer id) {
        return routeRepository.findById(id).orElse(null);
    }

    @GetMapping("/routes")
    public List<Route> getAllPlanets() {
        return routeRepository.findAll();
    }
}
