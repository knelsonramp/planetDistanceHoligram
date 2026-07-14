package com.example.nuclearfissioncore.repositoryies;

import com.example.nuclearfissioncore.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {
    Optional<Planet> findByNode(String node);
}
