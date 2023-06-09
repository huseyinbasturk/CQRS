package com.hb.ProductsService.command.api.events;


import com.hb.ProductsService.command.api.data.Product;
import com.hb.ProductsService.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {
    @Autowired
    private ProductRepository productRepository;
    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
        throw new Exception("Something occured");

    }
    @ExceptionHandler
    public void handle(Exception e) throws Exception {
        throw e;
    }
}
