package com.example.products.rest;

import java.util.UUID;

import com.example.products.command.CreateProductCommand;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    
    private final Environment env;
    private final CommandGateway commandGateway;
    
    @Autowired
    public ProductsController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel productRestModel) {     
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
        .price(productRestModel.getPrice())
        .quantity(productRestModel.getQuantity())
        .title(productRestModel.getTitle())
        .productId(UUID.randomUUID().toString())
        .build();

        String returnValue;
        try{
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception exception) {
            exception.printStackTrace();
            returnValue = exception.getLocalizedMessage();
        }

        return returnValue;
    }

    @GetMapping
    public String getProduct() {
        return "Get Product " + env.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduct() {
        return "Product Updated";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "Product Deleted";
    }
}
