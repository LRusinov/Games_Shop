package com.fmi.java.web.games_shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@IdClass(ShoppingCartItemId.class)
public class ShoppingCartItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "GAME_NAME")
    @EqualsAndHashCode.Include
    private Game game;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "CLIENT_USERNAME")
    @EqualsAndHashCode.Include
    private Client client;

    private int quantity;
}
