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

    public Integer getId() {
        return id;
    }

    public Integer getOriginPlanetId() {
        return originPlanetId;
    }

    public void setOriginPlanetId(Integer originPlanetId) {
        this.originPlanetId = originPlanetId;
    }

    public Integer getDestinationPlanetId() {
        return destinationPlanetId;
    }

    public void setDestinationPlanetId(Integer destinationPlanetId) {
        this.destinationPlanetId = destinationPlanetId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
