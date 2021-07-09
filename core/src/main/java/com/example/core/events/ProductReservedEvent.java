package com.example.core.events;

import lombok.Data;

@Data
public class ProductReservedEvent {

    private String productId;
    private int quantity;
    private String orderId;
    private String userId;

}
