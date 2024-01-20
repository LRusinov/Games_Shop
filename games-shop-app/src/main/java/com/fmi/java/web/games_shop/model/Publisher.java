package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PUBLISHER")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column(name = "NAME", length = 40, nullable = false, unique = true)
    String name;

    @Column(name = "LOGO_PICTURE_URL", nullable = false)
    String logoPictureUrl;

    @Column(name = "YEAR_OF_CREATION", nullable = false)
    int yearOfCreation;

    @Column(name = "DESCRIPTION", length = 350)
    String description;
}


