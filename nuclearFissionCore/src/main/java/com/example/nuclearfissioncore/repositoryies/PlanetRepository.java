package com.example.nuclearfissioncore.repositoryies;

import com.example.nuclearfissioncore.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {

}
