package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PUBLISHER")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "NAME", length = 40, nullable = false, unique = true)
    String name;

    @Column(name = "LOGO_PICTURE_URL", nullable = false)
    String logoPictureUrl;

    @Column(name = "YEAR_OF_CREATION", nullable = false)
    int yearOfCreation;

    @Column(name = "DESCRIPTION", length = 350)
    String description;

    protected Publisher() {
        //Needed for JPA.
    }

    public Publisher(final Long id, final String name, final String logoPictureUrl, final int yearOfCreation, final String description) {
        this.id = id;
        this.name = name;
        this.logoPictureUrl = logoPictureUrl;
        this.yearOfCreation = yearOfCreation;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogoPictureUrl() {
        return logoPictureUrl;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public String getDescription() {
        return description;
    }
}


