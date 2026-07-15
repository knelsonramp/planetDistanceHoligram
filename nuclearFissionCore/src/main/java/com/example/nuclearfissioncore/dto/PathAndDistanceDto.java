package com.example.nuclearfissioncore.dto;

import com.example.nuclearfissioncore.models.Planet;
import com.example.nuclearfissioncore.repositoryies.PlanetRepository;

import java.util.List;

public class PathAndDistanceDto {
    List<Integer> planetIdPath;
    List<Planet> planetPath;
    double distance;

    public PathAndDistanceDto(List<Integer> currentPath, double currentDistance) {
        this.planetIdPath = currentPath;
        this.distance = currentDistance;
    }

    public List<Integer> getPlanetIdPath() {
        return planetIdPath;
    }

    public double getDistance() {
        return distance;
    }

    public void setPlanetIdPath(List<Integer> planetIdPath) {
        this.planetIdPath = planetIdPath;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setPlanetPath(List<Planet> planetPath) {
        this.planetPath = planetPath;
    }

    public List<Planet> getPlanetPath() {
        return planetPath;
    }
}
