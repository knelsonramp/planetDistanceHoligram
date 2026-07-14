package com.example.nuclearfissioncore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PLANETS")
public class Planet {
    @Id
    private String name;

    public Planet() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
