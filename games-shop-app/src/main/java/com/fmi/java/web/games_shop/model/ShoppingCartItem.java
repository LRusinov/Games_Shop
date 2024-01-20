package com.fmi.java.web.games_shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(ShoppingCartItemId.class)
public class ShoppingCartItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "GAME_NAME")
    private Game game;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "CLIENT_USERNAME")
    private Client client;

    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Game game, Client client, int quantity) {
        this.game = game;
        this.client = client;
        this.quantity = quantity;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return Objects.equals(game, that.game) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, client);
    }
}
