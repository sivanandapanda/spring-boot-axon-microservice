package com.example.products.query;

import com.example.products.core.data.ProductsRepository;
import com.example.products.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductsQueryHandler {
    
    @Autowired
    ProductsRepository productsRepository;

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery findProductsQuery) {
        return productsRepository.findAll().stream()
        .map(dbModel -> {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(dbModel, productRestModel);
            return productRestModel;
        }).collect(Collectors.toList());
    }

}
