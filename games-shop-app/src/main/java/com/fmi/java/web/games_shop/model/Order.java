package com.fmi.java.web.games_shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PURCHASE_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "DATE_OF_CREATION", nullable = false)
    private Instant dateOfCreation;

    @Column(name = "ESTIMATED_DATE", nullable = false)
    private Instant estimatedDate;

    @Column(name = "DATE_OF_ARRIVAL")
    private Instant dateOfArrival;

    @ManyToOne
    @JoinColumn(name = "STATUS")
    private OrderStatus status;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
