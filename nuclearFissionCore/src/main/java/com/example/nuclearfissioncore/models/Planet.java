package com.example.nuclearfissioncore.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="PLANETS")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String node;

    public Planet(String name, String node) {
        this.name = name;
        this.node = node;
    }

    public Planet() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNode() { return node; }

    public void setNode(String node) {
        this.node = node;
    }

    public Integer getId() {
        return id;
    }


    public List<Integer> getDistanceToDestination(List<Integer> currentPlanetIdPath, Integer currentDistance, Integer destinationPlantId) {
        return null;
    }
}
