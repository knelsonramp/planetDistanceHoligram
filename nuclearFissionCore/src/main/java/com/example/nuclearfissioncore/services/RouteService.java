package com.example.nuclearfissioncore.services;

import com.example.nuclearfissioncore.dto.PathAndDistanceDto;
import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final PlanetRepository planetRepository;

    public RouteService(RouteRepository routeRepository, PlanetRepository planetRepository) {
        this.routeRepository = routeRepository;
        this.planetRepository = planetRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Integer id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Route createRoute(Route route) {
        if (route.getId() != null && routeRepository.existsById(route.getId())) { 
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Route already exists with id " + route.getId());
        }
        return routeRepository.save(route);
    }

    public Route updateRoute(Integer id, Route routeUpdates) {
        Route route = this.getRouteById(id);

        route.setOriginPlanetId(routeUpdates.getOriginPlanetId());
        route.setDestinationPlanetId(routeUpdates.getDestinationPlanetId());
        route.setDistance(routeUpdates.getDistance());
        route.setTrafficDelay(routeUpdates.getTrafficDelay());

        return routeRepository.save(route);
    }

    public void deleteRoute(Integer id) {
        if (!routeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No route found with id " + id);
        }
        routeRepository.deleteById(id);
    }

    public PathAndDistanceDto findShortestPath(Integer originPlanetId, Integer destinationPlanetId) {

        PathAndDistanceDto shortestPathAndDistance = new PathAndDistanceDto(new ArrayList<>(List.of(originPlanetId)), Double.POSITIVE_INFINITY);

        if(Objects.equals(originPlanetId, destinationPlanetId)) {
            shortestPathAndDistance.setDistance(0.0);
            this.populatePlanetPathFromPlanetIdPath(shortestPathAndDistance);
            return shortestPathAndDistance;
        }

        Queue<PathTracker> pathsToExplore = new ArrayDeque<>();

        List<Integer> startingPoint = new ArrayList<>(List.of(originPlanetId));
        double startingDistance = 0;
        PathTracker startingPath = new PathTracker(startingPoint, startingDistance);
        pathsToExplore.add(startingPath);

        double shortestDistance = Double.POSITIVE_INFINITY;
        while(!pathsToExplore.isEmpty()) {
            PathTracker currentPath = pathsToExplore.poll();

            if(currentPath.distance > shortestPathAndDistance.getDistance()) {
                continue;
            }

            int lastestPlanetIdInPath = currentPath.path.get(currentPath.path.size() - 1);

            if(Objects.equals(lastestPlanetIdInPath, destinationPlanetId)) {
                shortestPathAndDistance.setDistance(currentPath.distance);
                shortestPathAndDistance.setPlanetIdPath(new ArrayList<>(currentPath.path));
                continue;
            }

            List<Route> departureRoutes = routeRepository.findByOriginPlanetId(lastestPlanetIdInPath);

            for(Route departureRoute: departureRoutes) {
                if(currentPath.path.contains(departureRoute.getDestinationPlanetId())) {
                    continue;
                }

                List<Integer> newPath = new ArrayList<>(currentPath.path);
                newPath.add(departureRoute.getDestinationPlanetId());
                double newDistance = currentPath.distance + departureRoute.getDistance();
                PathTracker pathToExplore = new PathTracker(newPath, newDistance);
                pathsToExplore.add(pathToExplore);
            }
        }

        this.populatePlanetPathFromPlanetIdPath(shortestPathAndDistance);
        return shortestPathAndDistance;
    }

    public void populatePlanetPathFromPlanetIdPath(PathAndDistanceDto pathAndDistanceDto) {
        List<Integer> planetIdPath =  pathAndDistanceDto.getPlanetIdPath();
        List<Planet> planetPath = new ArrayList<>();

        for(Integer planetId: planetIdPath) {
            Optional<Planet> planetQuery = planetRepository.findById(planetId);

            if(planetQuery.isEmpty()) {
               continue;
            }
            Planet planet = planetQuery.get();
            planetPath.add(planet);
        }
        pathAndDistanceDto.setPlanetPath(planetPath);
    }

    static class PathTracker {
        List<Integer> path;
        double distance;

        PathTracker(List<Integer> currentPath, double currentDistance) {
            this.path = currentPath;
            this.distance = currentDistance;
        }
    }
}
