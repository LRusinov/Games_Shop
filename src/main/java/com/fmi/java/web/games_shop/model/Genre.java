package com.fmi.java.web.games_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GENRE")
public class Genre {
    @Id
    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    protected Genre() {
        //Needed for JPA.
    }

    public Genre(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
