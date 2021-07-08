package com.example.orders.core.events;

import com.example.orders.core.data.OrderStatus;
import lombok.Data;

@Data
public class OrderCreatedEvent {

    private String orderId;

    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
