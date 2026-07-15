package com.example.nuclearfissioncore;

import com.example.nuclearfissioncore.services.SeederService;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    private final SeederService seederService;

    public Seeder(SeederService seederService) {
        this.seederService = seederService;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        seederService.createPlanetsAndRoutes();
    }
}
