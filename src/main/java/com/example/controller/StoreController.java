package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import com.example.utils.ValidateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class StoreController {

    private final ProductService productService;

    public StoreController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> findAllProducts() {
        var list = productService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Integer id) {
        var product = productService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        ValidateUtil.validateInput(productDTO.price(), productDTO.name());
        productService.insert(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id,
                                           @RequestBody BigDecimal newPrice) {

        ValidateUtil.validatePrice(newPrice);
        productService.update(id, newPrice);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
