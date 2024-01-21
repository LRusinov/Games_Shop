package com.fmi.java.web.games_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ORDER_STATUS")
public class OrderStatus {

    @Id
    @Column(name = "STATUS_NAME", length = 30, nullable = false)
    private String statusName;
}
