package com.example.products.command;

import com.example.products.core.data.ProductLookupEntity;
import com.example.products.core.data.ProductLookupRepository;
import com.example.products.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    @Autowired
    private ProductLookupRepository repository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookupEntity productEntity = new ProductLookupEntity();
        BeanUtils.copyProperties(event, productEntity);

        repository.save(productEntity);
    }
}
