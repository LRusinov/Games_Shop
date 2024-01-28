package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
