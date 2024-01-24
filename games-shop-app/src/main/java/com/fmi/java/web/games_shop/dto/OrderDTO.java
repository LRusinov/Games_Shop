package com.fmi.java.web.games_shop.dto;

import java.time.Instant;
import java.util.List;

public record OrderDTO(int id, Instant dateOfCreation, Instant estimatedDate, Instant dateOfArrival, double totalPrice, List<OrderItemDTO> orderItems) {
}
