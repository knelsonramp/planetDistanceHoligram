package com.example.nuclearfissioncore.controllers;

import com.example.nuclearfissioncore.dto.PathAndDistanceDto;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.services.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes/{id}")
    public Route getRoute(@PathVariable Integer id) {
        return routeService.getRouteById(id);
    }

    @GetMapping("/routes")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @PostMapping("/routes")
    @ResponseStatus(HttpStatus.CREATED)
    public Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

    @PutMapping("/routes/{id}")
    public Route updateRoute(@PathVariable Integer id, @RequestBody Route route) {
        return routeService.updateRoute(id, route);
    }

    @DeleteMapping("/routes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoute(@PathVariable Integer id) {
        routeService.deleteRoute(id);
    }

    @GetMapping("/shortestPathBetweenPlanets")
    public PathAndDistanceDto getDistanceBetweenPlanets(@RequestParam Integer originPlanetId, @RequestParam Integer destinationPlanetId) {
        return routeService.findShortestPath(originPlanetId, destinationPlanetId);
    }
}
