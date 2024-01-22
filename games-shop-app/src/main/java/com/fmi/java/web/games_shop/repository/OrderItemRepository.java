package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
