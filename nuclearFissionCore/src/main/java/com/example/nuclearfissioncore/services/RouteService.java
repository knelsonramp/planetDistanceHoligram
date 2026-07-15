package com.example.nuclearfissioncore.services;

import com.example.nuclearfissioncore.dto.PathAndDistanceDto;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public PathAndDistanceDto findShortestPath(Integer originPlanetId, Integer destinationPlanetId) {

        PathAndDistanceDto shortestPathAndDistance = new PathAndDistanceDto(new ArrayList<>(List.of(originPlanetId)), Double.POSITIVE_INFINITY);

        if(Objects.equals(originPlanetId, destinationPlanetId)) {
            shortestPathAndDistance.setDistance(0.0);
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
                shortestPathAndDistance.setPath(new ArrayList<>(currentPath.path));
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

        return shortestPathAndDistance;
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
