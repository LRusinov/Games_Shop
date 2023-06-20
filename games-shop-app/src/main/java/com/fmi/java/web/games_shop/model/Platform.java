package com.fmi.java.web.games_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "PLATFORM")
public class Platform {
    protected Platform() {
        //Needed for JPA.
    }

    public Platform(final String name) {
        this.name = name;
    }

    @Id
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return Objects.equals(name, platform.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
