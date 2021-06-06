package com.example.products.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    
    @Autowired
    private Environment env;

    @PostMapping
    public String createProduct() {
        return "Product Created";
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
