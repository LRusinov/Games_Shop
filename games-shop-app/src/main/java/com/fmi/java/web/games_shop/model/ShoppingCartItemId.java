package com.fmi.java.web.games_shop.model;

import java.io.Serializable;
import java.util.Objects;

public class ShoppingCartItemId implements Serializable {

    private String game;
    private String client;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItemId that = (ShoppingCartItemId) o;
        return Objects.equals(game, that.game) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, client);
    }
}
