package com.example.nuclearfissioncore.services;

import com.example.nuclearfissioncore.dto.PlanetWithRoutesDto;
import com.example.nuclearfissioncore.dto.RouteDto;
import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final RouteRepository routeRepository;

    public PlanetService(PlanetRepository planetRepository, RouteRepository routeRepository) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
    }

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Planet getPlanet(Integer planetId) {
        return planetRepository.findById(planetId).orElse(null);
    }

    public List<PlanetWithRoutesDto> getPlanetsWithRoutes() {
        List<Planet> planets = planetRepository.findAll();
        List<Route> routes = routeRepository.findAll();

        Map<Integer, Planet> planetsById = new HashMap<>();
        for (Planet planet : planets) {
            planetsById.put(planet.getId(), planet);
        }

        List<PlanetWithRoutesDto> planetsWithRoutes = new ArrayList<>();

        for(Planet planet: planets) {
            PlanetWithRoutesDto planetWithRoutes = new PlanetWithRoutesDto(planet.getId(), planet.getName());
            List<RouteDto> routeDtos = new ArrayList<>();
            for(Route route: routes) {
                if(Objects.equals(planet.getId(), route.getOriginPlanetId())) {
                    Planet destinationPlanet = planetsById.get(route.getDestinationPlanetId());
                    RouteDto routeDto = new RouteDto(route.getDestinationPlanetId(), destinationPlanet.getName(), route.getDistance());
                    routeDtos.add(routeDto);
                }
            }
            planetWithRoutes.setRoutes(routeDtos);
            planetsWithRoutes.add(planetWithRoutes);
        }

        return planetsWithRoutes;
    }
}
