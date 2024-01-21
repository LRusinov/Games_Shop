package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Order;
import com.fmi.java.web.games_shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public Order getOrder(final int orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id \"%d\" does not exist.", orderId)));
    }

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public Order completeOrder(Order order){
        return orderRepository.save(order);
    }
}
