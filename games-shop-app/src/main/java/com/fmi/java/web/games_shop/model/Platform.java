package com.fmi.java.web.games_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PLATFORM")
public class Platform {

    @Id
    @Column(name = "name", length = 30, nullable = false)
    private String name;
}
