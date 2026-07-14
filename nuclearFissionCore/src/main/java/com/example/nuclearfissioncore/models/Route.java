package com.example.nuclearfissioncore.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ROUTES")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer originPlanetId;
    private Integer destinationPlanetId;
    private Double distance;
    private Double trafficDelay;

    public Route() {};

    public Route(Integer originPlanetId, Integer destinationPlanetId, Double distance, Double trafficDelay) {
        this.originPlanetId = originPlanetId;
        this.destinationPlanetId = destinationPlanetId;
        this.distance = distance;
        this.trafficDelay = trafficDelay;
    };

    public Integer getId() {
        return id;
    }

    public Integer getOriginPlanetId() {
        return originPlanetId;
    }

    public Double getTrafficDelay() {
        return trafficDelay;
    }

    public void setTrafficDelay(Double trafficDelay) {
        this.trafficDelay = trafficDelay;
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

    public List<Integer> getDistanceToDestination(List<Integer> currentPlanetIdPath, Double currentDistance, Integer destinationPlantId) {
        currentDistance += distance;
        return null;
    }

}
