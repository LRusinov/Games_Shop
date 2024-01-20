package com.fmi.java.web.games_shop.repository;

import com.fmi.java.web.games_shop.model.ShoppingCartItem;
import com.fmi.java.web.games_shop.model.ShoppingCartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, ShoppingCartItemId> {
}
