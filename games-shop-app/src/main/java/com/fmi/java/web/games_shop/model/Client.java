package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @Column(name = "USERNAME", length = 30, nullable = false)
    @EqualsAndHashCode.Include
    private String username;

    @Column(name = "PASSWORD", length = 20, nullable = false)
    @EqualsAndHashCode.Include
    private String password;

    @OneToMany(mappedBy = "client")
    private Set<ShoppingCartItem> shoppingCartItems;
}
