package com.example.products.command.rest;

import com.example.products.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {
    
    private final Environment env;
    private final CommandGateway commandGateway;
    
    @Autowired
    public ProductsCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel productRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
        .price(productRestModel.getPrice())
        .quantity(productRestModel.getQuantity())
        .title(productRestModel.getTitle())
        .productId(UUID.randomUUID().toString())
        .build();

        /*try{
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception exception) {
            exception.printStackTrace();
            returnValue = exception.getLocalizedMessage();
        }*/

        return commandGateway.sendAndWait(createProductCommand);
    }

    /* @PutMapping
    public String updateProduct() {
        return "Product Updated";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "Product Deleted";
    } */
}
