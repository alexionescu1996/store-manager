package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class StoreController {

    private final ProductService productService;

    public StoreController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> get() {
        List<ProductDTO> list = productService.findAll();
        return list;
    }

    @PostMapping
    public void add(@RequestBody ProductDTO productDTO) {
        productService.insert(productDTO);
    }


}
