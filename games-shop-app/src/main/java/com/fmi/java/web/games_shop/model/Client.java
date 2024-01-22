package com.fmi.java.web.games_shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "USERNAME")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name ="ROLE")
    private ClientRole role;

    @OneToMany(mappedBy = "client")
    private List<ShoppingCartItem> shoppingCartItems;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    public Client(String username, String password, ClientRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
