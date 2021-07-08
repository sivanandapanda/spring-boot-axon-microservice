package com.example.orders.query;

import com.example.orders.core.data.OrdersRepository;
import com.example.orders.query.rest.OrderRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdersQueryHandler {

    @Autowired
    private OrdersRepository repository;

    @QueryHandler
    public List<OrderRestModel> findOrders(FindOrdersQuery findOrdersQuery) {
        return repository.findAll().stream()
                .map(dbModel -> {
                    OrderRestModel orderRestModel = new OrderRestModel();
                    BeanUtils.copyProperties(dbModel, orderRestModel);
                    return orderRestModel;
                })
                .collect(Collectors.toList());
    }
}
