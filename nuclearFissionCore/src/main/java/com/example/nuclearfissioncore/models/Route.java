package com.example.nuclearfissioncore.models;

import jakarta.persistence.*;

@Entity
@Table(name="ROUTES")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer originPlanetId;
    private Integer destinationPlanetId;
    private Double distance;

    public Route() {};

    public Route(Integer originPlanetId, Integer destinationPlanetId, Double distance) {
        this.originPlanetId = originPlanetId;
        this.destinationPlanetId = destinationPlanetId;
        this.distance = distance;
    };
}
