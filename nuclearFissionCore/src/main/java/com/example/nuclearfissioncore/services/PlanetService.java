package com.example.nuclearfissioncore.services;

import com.example.nuclearfissioncore.dto.PlanetWithRoutesDto;
import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Planet getPlanet(Integer planetId) {
        return planetRepository.findById(planetId).orElse(null);
    }

    public List<PlanetWithRoutesDto> getPlanetWithRoutes() {
        return null;
    }
}
