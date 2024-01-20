package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @Column(name = "USERNAME", length = 30, nullable = false)
    private String username;

    @Column(name = "PASSWORD", length = 20, nullable = false)
    private String password;

    @OneToMany(mappedBy = "client")
    private Set<ShoppingCartItem> shoppingCartItems;

    protected Client() {
        //Needed for JPA.
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(Set<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return Objects.equals(username, client.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
