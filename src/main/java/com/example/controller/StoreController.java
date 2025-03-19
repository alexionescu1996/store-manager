package com.example.controller;

import com.example.constants.Constants;
import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class StoreController {

    private final ProductService productService;

    public StoreController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAllProducts() {
        var list = productService.findAll();
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Integer id) {
        var product = productService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDTO productDTO) {
        productService.insert(productDTO);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Integer id,
                              @RequestBody ProductDTO dto) {

        productService.update(id, dto.price());
    }

}
