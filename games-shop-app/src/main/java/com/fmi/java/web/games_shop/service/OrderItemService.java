package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.model.OrderItem;
import com.fmi.java.web.games_shop.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem createOrderItem(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
