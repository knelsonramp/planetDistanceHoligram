package com.example.nuclearfissioncore.dto;

import com.example.nuclearfissioncore.repositoryies.PlanetRepository;

import java.util.List;

public class PathAndDistanceDto {
    List<Integer> path;
    double distance;

    public PathAndDistanceDto(List<Integer> currentPath, double currentDistance) {
        this.path = currentPath;
        this.distance = currentDistance;
    }

    public List<Integer> getPath() {
        return path;
    }

    public double getDistance() {
        return distance;
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
