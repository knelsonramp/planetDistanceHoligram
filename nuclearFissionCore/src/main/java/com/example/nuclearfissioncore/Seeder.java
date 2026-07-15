package com.example.nuclearfissioncore;

import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import com.example.nuclearfissioncore.services.SeederService;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    private final PlanetRepository planetRepository;
    private final RouteRepository routeRepository;
    private final SeederService seederService;

    public Seeder(PlanetRepository planetRepository, RouteRepository routeRepository, SeederService seederService) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
        this.seederService = seederService;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        seederService.createPlanetsAndRoutes();
    }
}
