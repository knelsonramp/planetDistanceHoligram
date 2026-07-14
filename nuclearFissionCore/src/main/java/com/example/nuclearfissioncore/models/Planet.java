package com.example.nuclearfissioncore.models;

import jakarta.persistence.*;

@Entity
@Table(name="PLANETS")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Planet(String name) {
        this.name = name;
    }

    public Planet() {}

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
