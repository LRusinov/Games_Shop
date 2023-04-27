package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "GAME")
public class Game {
    @Id
    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "RELEASE_DATE")
    private Instant releaseDate;

    @Column(name = "PRICE")
    private double price;

    @ManyToOne(targetEntity = Platform.class)
    @JoinColumn(name = "PLATFORM", referencedColumnName = "NAME")
    private Platform platform;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PICTURE")
    private String picture;

    @ManyToOne(targetEntity = Publisher.class)
    @JoinColumn(name = "PUBLISHER_ID", referencedColumnName = "ID")
    private Publisher publisher;

    @ManyToMany(targetEntity = Genre.class,fetch = FetchType.EAGER)
    @JoinTable(name = "GAME_GENRE", joinColumns = @JoinColumn(name = "GAME_NAME"),inverseJoinColumns = @JoinColumn(name = "GENRE_NAME"))
    private Set<Genre> genres;

    public String getName() {
        return name;
    }

    protected Game() {
    }

    public Game(String name, Instant releaseDate, double price, Platform platform, String description, String picture, Publisher publisher, Set<Genre> genres) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.price = price;
        this.platform = platform;
        this.description = description;
        this.picture = picture;
        this.publisher = publisher;
        this.genres = genres;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
