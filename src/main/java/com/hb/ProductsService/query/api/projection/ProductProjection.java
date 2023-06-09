package com.hb.ProductsService.query.api.projection;

import com.hb.ProductsService.command.api.data.Product;
import com.hb.ProductsService.command.api.data.ProductRepository;
import com.hb.ProductsService.command.api.model.ProductRestModel;
import com.hb.ProductsService.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {
    @Autowired
    private ProductRepository productRepository;
    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
        List<Product> products = productRepository.findAll();

        List<ProductRestModel> productRestModels = products.stream().map(product ->
            ProductRestModel.builder()
                    .quantity(product.getQuantity())
                    .price(product.getPrice())
                    .name(product.getName())
                    .build())
            .toList();

        return productRestModels;

    }
}
