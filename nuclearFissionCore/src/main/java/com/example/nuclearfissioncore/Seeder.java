package com.example.nuclearfissioncore;

import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    private final PlanetRepository planetRepository;
    public Seeder(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        planetRepository.save(new Planet("A"));
        planetRepository.save(new Planet("B"));
        planetRepository.save(new Planet("C"));
    }
}
