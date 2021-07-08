package com.example.orders.command;

import com.example.orders.core.data.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    private final String userId;
    private final String productId;
    private final int quantity;
    private final String addressId;
    private final OrderStatus orderStatus;
}
