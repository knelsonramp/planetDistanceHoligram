package com.example.nuclearfissioncore;

import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.models.Route;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import com.example.nuclearfissioncore.repositoryies.RouteRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    private final PlanetRepository planetRepository;
    private final RouteRepository routeRepository;

    public Seeder(PlanetRepository planetRepository, RouteRepository routeRepository) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        planetRepository.save(new Planet("A"));
        planetRepository.save(new Planet("B"));
        planetRepository.save(new Planet("C"));
        routeRepository.save(new Route(1, 2, 0.30));
        routeRepository.save(new Route(1, 3, 0.60));
        routeRepository.save(new Route(2, 3, 0.30));
    }
}
