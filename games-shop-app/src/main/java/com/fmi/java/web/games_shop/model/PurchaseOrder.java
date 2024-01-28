package com.fmi.java.web.games_shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder {

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

    @Enumerated(value = EnumType.STRING)
    @Column(name ="STATUS")
    private OrderStatus status;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderItem> orderItems;

    public PurchaseOrder(Instant dateOfCreation, Instant dateOfArrival, Client client, List<OrderItem> orderItems) {
        this.dateOfCreation = dateOfCreation;
        estimateDateOfArrival();
        this.dateOfArrival = dateOfArrival;
        setOrderStatus(dateOfArrival);
        calculateTotalPrice(orderItems);
        this.client = client;
        this.orderItems = orderItems;
    }

    private void estimateDateOfArrival() {
        this.estimatedDate = Instant.now().plus(7, ChronoUnit.DAYS);
    }

    private void calculateTotalPrice(List<OrderItem> orderItems){
        this.totalPrice = orderItems.stream().mapToDouble(item->item.getQuantity() * item.getPrice()).sum();
    }

    private void setOrderStatus(Instant dateOfArrival) {
        if(dateOfArrival == null){
            this.status = OrderStatus.IN_PROGRESS;
            return;
        }
        this.status = OrderStatus.COMPLETED;
    }
}
