package com.example.nuclearfissioncore.services;

import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.*;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Double findShortestDistance(Integer originPlanetId, Integer destinationPlanetId) {
        if(Objects.equals(originPlanetId, destinationPlanetId)) {
            return 0.0;
        }

        Queue<PathTracker> pathsToExplore = new ArrayDeque<>();

        List<Integer> startingPoint = new ArrayList<>(List.of(originPlanetId));
        double currentDistance = 0;
        PathTracker startingPath = new PathTracker(startingPoint, currentDistance);
        pathsToExplore.add(startingPath);

        double minDistance = Double.POSITIVE_INFINITY;
        while(!pathsToExplore.isEmpty()) {
            PathTracker currentPath = pathsToExplore.poll();

            if(currentPath.distance > minDistance) {
                continue;
            }

            int lastestPlanetIdInPath = currentPath.path.get(currentPath.path.size() - 1);

            if(Objects.equals(lastestPlanetIdInPath, destinationPlanetId)) {
                minDistance = currentPath.distance;
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

        return minDistance;
    }

    static class PathTracker {
        List<Integer> path;
        double distance;

        PathTracker(List<Integer> currentPath, double currentDistance) {
            this.path = currentPath;
            this.distance = currentDistance;
        }
    }

    private List<Integer> getDestinationPlanetIdsFromRoutes(List<Route> departureRoutes) {
        if(departureRoutes.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> destinationPlanetIds = new ArrayList<>();
        for(Route route: departureRoutes) {
            destinationPlanetIds.add(route.getDestinationPlanetId());
        }
        return destinationPlanetIds;
    }
}
