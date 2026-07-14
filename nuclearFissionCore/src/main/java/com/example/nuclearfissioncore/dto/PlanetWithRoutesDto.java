package com.example.nuclearfissioncore.dto;

import java.util.List;

public class PlanetWithRoutesDto {
    private Integer planetId;
    private String planetName;
    private List<RouteDto> routes;

    public PlanetWithRoutesDto(Integer planetId, String planetName) {
        this.planetId = planetId;
        this.planetName = planetName;
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
}
