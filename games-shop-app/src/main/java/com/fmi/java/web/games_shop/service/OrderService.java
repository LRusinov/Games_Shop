package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.GameDto;
import com.fmi.java.web.games_shop.dto.OrderItemDTO;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Order;
import com.fmi.java.web.games_shop.model.OrderItem;
import com.fmi.java.web.games_shop.model.ShoppingCartItemId;
import com.fmi.java.web.games_shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final GameService gameService;
    private final ShoppingCartItemService shoppingCartItemService;
    private final OrderItemService orderItemService;

    public Order getOrder(final int orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id \"%d\" does not exist.", orderId)));
    }

    public Order createOrder(String clientUsername, Order order){
        List<OrderItem> orderItems = order.getOrderItems();
        Order newOrder = orderRepository.save(order);
        orderItems.forEach(orderItem -> {
            orderItem.setOrder(newOrder);
            orderItemService.createOrderItem(orderItem);
            shoppingCartItemService.removedShoppingCartItem(new ShoppingCartItemId(orderItem.getGameName(), clientUsername));
        });
        return newOrder;
    }

    public Order completeOrder(Order order){
        return orderRepository.save(order);
    }
}
