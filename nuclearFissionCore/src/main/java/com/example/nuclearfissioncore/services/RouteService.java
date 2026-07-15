package com.example.nuclearfissioncore.services;

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

    public Double findShortestDistance(Integer originPlanetId, Integer destinationPlanetId) {
        if(Objects.equals(originPlanetId, destinationPlanetId)) {
            return 0.0;
        }

        return -1.0;
    }
}
