package com.example.service;

import com.example.dto.ProductDTO;
import com.example.exception.ProductNotFoundException;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDTO::fromEntity)
                .toList();
    }

    public ProductDTO findById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());

        return ProductDTO.fromEntity(product);
    }

    @Transactional
    public void insert(ProductDTO productDTO) {
        Product newProduct = ProductDTO.toEntity(productDTO);
        productRepository.save(newProduct);
    }

    @Transactional
    public void update(Integer id, BigDecimal newPrice) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.setPrice(newPrice);
    }
}
