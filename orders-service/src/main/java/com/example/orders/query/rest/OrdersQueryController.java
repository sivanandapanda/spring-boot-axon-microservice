package com.example.orders.query.rest;

import com.example.orders.query.FindOrdersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<OrderRestModel> getOrders() {
        return queryGateway.query(new FindOrdersQuery(),
                ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();
    }
}
