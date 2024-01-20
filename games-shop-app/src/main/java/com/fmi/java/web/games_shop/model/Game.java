package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "GAME")
public class Game {
    @Id
    @Column(name = "NAME", length = 50, nullable = false)
    @EqualsAndHashCode.Include
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

    public String getPublisherName() {
        return publisher.getName();
    }
}
