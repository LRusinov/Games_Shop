package com.fmi.java.web.games_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Login implements Serializable {

    @Id
    @Column(name = "USERNAME", length = 30, nullable = false)
    @EqualsAndHashCode.Include
    private String username;

    @Column(name = "PASSWORD", length = 20, nullable = false)
    private String password;
}
