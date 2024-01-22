package com.fmi.java.web.games_shop.dto;

import java.util.List;

public record PurchaseOrderDTO (String clientUsername, List<OrderItemDTO> orderItems){}
