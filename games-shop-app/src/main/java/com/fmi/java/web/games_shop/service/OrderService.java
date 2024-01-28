package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.PurchaseOrder;
import com.fmi.java.web.games_shop.model.OrderItem;
import com.fmi.java.web.games_shop.model.ShoppingCartItemId;
import com.fmi.java.web.games_shop.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final GameService gameService;
    private final ShoppingCartItemService shoppingCartItemService;
    private final OrderItemService orderItemService;

    public PurchaseOrder getOrder(final int orderId){
        return purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id \"%d\" does not exist.", orderId)));
    }

    public PurchaseOrder createOrder(String clientUsername, PurchaseOrder purchaseOrder){
        List<OrderItem> orderItems = purchaseOrder.getOrderItems();
        PurchaseOrder newPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        orderItems.forEach(orderItem -> {
            orderItem.setPurchaseOrder(newPurchaseOrder);
            orderItemService.createOrderItem(orderItem);
            shoppingCartItemService.removedShoppingCartItem(new ShoppingCartItemId(orderItem.getGameName(), clientUsername));
        });
        return newPurchaseOrder;
    }

    public PurchaseOrder completeOrder(PurchaseOrder purchaseOrder){
        return purchaseOrderRepository.save(purchaseOrder);
    }
}
