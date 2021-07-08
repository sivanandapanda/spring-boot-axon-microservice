package com.example.orders.command;

import com.example.orders.core.data.OrderStatus;
import com.example.orders.core.events.OrderCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        if(createOrderCommand.getProductId() == null || createOrderCommand.getProductId().isBlank()) {
            throw new IllegalArgumentException("Product id can't be null");
        }

        if(createOrderCommand.getAddressId() == null || createOrderCommand.getAddressId().isBlank()) {
            throw new IllegalArgumentException("Address id can't be null");
        }

        if(createOrderCommand.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity can't be less than or equal to 0");
        }

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);

        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        this.orderId = orderCreatedEvent.getOrderId();
        this.productId = orderCreatedEvent.getProductId();
        this.userId = orderCreatedEvent.getUserId();
        this.quantity = orderCreatedEvent.getQuantity();
        this.addressId = orderCreatedEvent.getAddressId();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
    }
}
