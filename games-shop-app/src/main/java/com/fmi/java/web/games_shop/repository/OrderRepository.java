package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
