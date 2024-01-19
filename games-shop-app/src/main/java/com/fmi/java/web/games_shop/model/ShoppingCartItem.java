package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;

@Entity
@IdClass(ShoppingCartItemId.class)
public class ShoppingCartItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "GAME_NAME")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "CLIENT_USERNAME")
    private Game client;

    private int quantity;
}
