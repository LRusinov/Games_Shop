package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
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
    @EqualsAndHashCode.Include
    @Column(name = "USERNAME")
    private String username;

    @OneToMany(mappedBy = "client")
    private List<ShoppingCartItem> shoppingCartItems;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

}
