package com.example.nuclearfissioncore.repositoryies;

import com.example.nuclearfissioncore.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    List<Route> findByOriginPlanetId(Integer originPlanetId);
    List<Route> findByDestinationPlanetId(Integer destinationPlanetId);
    Optional<Route> findByOriginPlanetIdAndDestinationPlanetId(Integer originPlanetId, Integer destinationPlanetId);
}
