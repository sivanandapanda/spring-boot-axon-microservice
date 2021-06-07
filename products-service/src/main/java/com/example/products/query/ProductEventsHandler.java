package com.example.products.query;

import com.example.products.core.data.ProductEntity;
import com.example.products.core.data.ProductsRepository;
import com.example.products.core.events.ProductCreatedEvent;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    
    @Autowired
    private ProductsRepository repository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        repository.save(productEntity);
    }

}
