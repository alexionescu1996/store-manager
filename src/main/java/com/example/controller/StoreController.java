package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class StoreController {

    private final ProductRepository productRepository;

    public StoreController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> get() {
        List<Product> list = productRepository.findAll();
        return list;
    }
}
