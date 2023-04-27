package com.fmi.java.web.games_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PLATFORM")
public class Platform {
    protected Platform() {

    }

    public Platform(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "name",length = 30, nullable = false)
    private String name;

    public String getName() {
        return name;
    }
}
