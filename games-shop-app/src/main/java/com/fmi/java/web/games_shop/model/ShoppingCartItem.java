package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Id
    @ManyToOne
    @JoinColumn(name = "CLIENT_USERNAME", referencedColumnName = "USERNAME")
    @EqualsAndHashCode.Include
    private Client client;

    private int quantity;
}
