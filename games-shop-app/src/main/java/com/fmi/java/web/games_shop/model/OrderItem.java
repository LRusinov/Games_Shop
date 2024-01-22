package com.fmi.java.web.games_shop.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "GAME_NAME", referencedColumnName = "NAME")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private Order order;

    private int quantity;

    public OrderItem(Game game, int quantity) {
        this.game = game;
        this.quantity = quantity;
    }
}
