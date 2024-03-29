package com.example.products.command;

import com.example.core.events.ProductReservedEvent;
import com.example.products.core.data.ProductEntity;
import com.example.products.core.data.ProductsRepository;
import com.example.products.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {
    
    @Autowired
    private ProductsRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);


    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        try {
            repository.save(productEntity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        //throw new Exception("An error took place in @CommandHandler");
    }

    @EventHandler
    public void on(ProductReservedEvent event) throws Exception {
        var productEntity = repository.findByProductId(event.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - event.getQuantity());
        repository.save(productEntity);

        LOGGER.info("ProductReservedEvent for orderId {} and productId {}", event.getOrderId(), event.getProductId());
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) {

    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
