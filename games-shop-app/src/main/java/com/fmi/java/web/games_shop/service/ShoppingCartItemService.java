package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.model.ShoppingCartItem;
import com.fmi.java.web.games_shop.repository.ShoppingCartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;

    public ShoppingCartItemService(ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }

    public ShoppingCartItem addShoppingCartItem(final ShoppingCartItem shoppingCartItem) {
        return shoppingCartItemRepository.save(shoppingCartItem);
    }
}
