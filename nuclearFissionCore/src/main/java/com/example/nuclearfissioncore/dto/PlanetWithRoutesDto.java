package com.example.nuclearfissioncore.dto;

import java.util.List;

public class PlanetWithRoutesDto {
    private Integer planetId;
    private String planetName;
    private String planetNode;
    private List<RouteDto> routes;

    public PlanetWithRoutesDto(Integer planetId, String planetName, String planetNode) {
        this.planetId = planetId;
        this.planetName = planetName;
        this.planetNode = planetNode;
    }

    public String getPlanetNode() {
        return planetNode;
    }

    public void setPlanetNode(String planetNode) {
        this.planetNode = planetNode;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public void setPlanetId(Integer planetId) {
        this.planetId = planetId;
    }

    public void setRoutes(List<RouteDto> routes) {
        this.routes = routes;
    }

    public Integer getPlanetId() {
        return planetId;
    }

    public String getPlanetName() {
        return planetName;
    }

    public List<RouteDto> getRoutes() {
        return routes;
    }
}
