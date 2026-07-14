package com.example.nuclearfissioncore.dto;

import com.example.nuclearfissioncore.models.Route;

public class RouteDto {
    private Integer destinationPlanetId;
    private String destinationPlanetName;
    private String destinationPlanetNode;
    private Double distance;

    public RouteDto(Integer destinationPlanetId, String destinationPlanetName, Double distance, String destinationPlanetNode) {
        this.destinationPlanetId = destinationPlanetId;
        this.destinationPlanetName = destinationPlanetName;
        this.distance = distance;
        this.destinationPlanetNode = destinationPlanetNode;
    }

    public void setDesinationPlanetId(Integer destinationPlanetId) {
        this.destinationPlanetId = destinationPlanetId;
    }

    public void setDestinationPlanetName(String destinationPlanetName) {
        this.destinationPlanetName = destinationPlanetName;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDestinationPlanetNode() {
        return destinationPlanetNode;
    }

    public void setDestinationPlanetNode(String node) {
        this.destinationPlanetNode = node;
    }

    public Integer getDestinationPlanetId() {
        return destinationPlanetId;
    }

    public String getDestinationPlanetName() {
        return destinationPlanetName;
    }

    public Double getDistance() {
        return distance;
    }
}
