package com.fmi.java.web.games_shop.dto;

import com.fmi.java.web.games_shop.model.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderDTO(int id, Instant dateOfCreation, Instant estimatedDate, Instant dateOfArrival, double totalPrice, String status, List<OrderItemDTO> orderItems) {
}
