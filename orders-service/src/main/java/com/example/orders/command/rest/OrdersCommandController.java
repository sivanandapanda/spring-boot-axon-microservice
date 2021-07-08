package com.example.orders.command.rest;

import com.example.orders.command.CreateOrderCommand;
import com.example.orders.core.data.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("orders")
public class OrdersCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createOrder(@Valid @RequestBody CreateOrderRestModel orderRestModel) {
        var createOrderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .productId(orderRestModel.getProductId())
                .addressId(orderRestModel.getAddressId())
                .quantity(orderRestModel.getQuantity())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .orderStatus(OrderStatus.CREATED)
                .build();

        return commandGateway.sendAndWait(createOrderCommand);
    }
}
