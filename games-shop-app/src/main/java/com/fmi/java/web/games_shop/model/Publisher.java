package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;

import java.util.Objects;

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

    public Publisher(final Long id, final String name, final String logoPictureUrl, final int yearOfCreation,
                     final String description) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return yearOfCreation == publisher.yearOfCreation && Objects.equals(id, publisher.id) && Objects.equals(name, publisher.name) && Objects.equals(logoPictureUrl, publisher.logoPictureUrl) && Objects.equals(description, publisher.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, logoPictureUrl, yearOfCreation, description);
    }
}


