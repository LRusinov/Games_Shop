package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "GAME")
public class Game {
    @Id
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "RELEASE_DATE", nullable = false)
    private Instant releaseDate;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @ManyToMany(targetEntity = Platform.class, fetch = FetchType.EAGER)
    @JoinTable(name = "GAME_PLATFORM", joinColumns = @JoinColumn(name = "GAME_NAME"), inverseJoinColumns =
    @JoinColumn(name = "PLATFORM_NAME"))
    private Set<Platform> platforms;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PICTURE_URL", nullable = false)
    private String pictureUrl;

    @ManyToOne(targetEntity = Publisher.class)
    @JoinColumn(name = "PUBLISHER_ID", referencedColumnName = "ID")
    private Publisher publisher;

    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.EAGER)
    @JoinTable(name = "GAME_GENRE", joinColumns = @JoinColumn(name = "GAME_NAME"), inverseJoinColumns =
    @JoinColumn(name = "GENRE_NAME"))
    private Set<Genre> genres;

    public String getName() {
        return name;
    }

    protected Game() {
        //Needed for JPA.
    }

    public Game(final String name, final Instant releaseDate, final double price, final Set<Platform> platforms,
                final String description, final String pictureUrl, final Publisher publisher, final Set<Genre> genres) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.price = price;
        this.platforms = platforms;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.publisher = publisher;
        this.genres = genres;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getPublisherName() {
        return publisher.getName();
    }

    public void setReleaseDate(final Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public void setPlatform(final Set<Platform> platforms) {
        this.platforms = platforms;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setPictureUrl(final String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setPublisher(final Publisher publisher) {
        this.publisher = publisher;
    }

    public void setGenres(final Set<Genre> genres) {
        this.genres = genres;
    }
}
