package com.example.nuclearfissioncore.services;

import com.example.nuclearfissioncore.dto.PlanetWithRoutesDto;
import com.example.nuclearfissioncore.dto.RouteDto;
import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Planet createPlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public Planet updatePlanet(Integer planetId, Planet planetUpdates) {
        Planet planet = planetRepository.findById(planetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No planet found with id " + planetId));

        planet.setName(planetUpdates.getName());
        planet.setNode(planetUpdates.getNode());

        return planetRepository.save(planet);
    }

    public void deletePlanet(Integer planetId) {
        if (!planetRepository.existsById(planetId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No planet found with id " + planetId);
        }
        planetRepository.deleteById(planetId);
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
            PlanetWithRoutesDto planetWithRoutes = new PlanetWithRoutesDto(planet.getId(), planet.getName(), planet.getNode());
            List<RouteDto> routeDtos = new ArrayList<>();
            for(Route route: routes) {
                if(Objects.equals(planet.getId(), route.getOriginPlanetId())) {
                    Planet destinationPlanet = planetsById.get(route.getDestinationPlanetId());
                    RouteDto routeDto = new RouteDto(route.getDestinationPlanetId(), destinationPlanet.getName(), route.getDistance(), destinationPlanet.getNode(), route.getTrafficDelay());
                    routeDtos.add(routeDto);
                }
            }
            planetWithRoutes.setRoutes(routeDtos);
            planetsWithRoutes.add(planetWithRoutes);
        }

        return planetsWithRoutes;
    }
}
