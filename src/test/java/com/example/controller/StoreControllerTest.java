package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.exception.GlobalExceptionHandler;
import com.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StoreControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private StoreController storeController;

    private MockMvc mvc;

    @BeforeEach
    void init() {
        mvc = MockMvcBuilders.standaloneSetup(storeController)
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void test_get_all_when_success() throws Exception {
        when(productService.findAll()).thenReturn(productList());

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(productService, times(1)).findAll();
    }

    @Test
    void test_get_all_when_list_is_empty() throws Exception {
        when(productService.findAll()).thenReturn(Collections.emptyList());

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(productService, times(1)).findAll();
    }

    @Test
    void test_get_all_when_exception() throws Exception {

        when(productService.findAll()).thenThrow(new RuntimeException(":("));

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(productService, times(1)).findAll();
    }

    @Test
    void test_findById_when_success() throws Exception {
        ProductDTO product = new ProductDTO(1, "test", BigDecimal.valueOf(1.124));

        when(productService.findById(1)).thenReturn(product);

        mvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(productService, times(1)).findById(1);
    }


    private List<ProductDTO> productList() {
        return Arrays.asList(
                new ProductDTO(1, "test", BigDecimal.valueOf(1.2)),
                new ProductDTO(2, "test2", BigDecimal.valueOf(5.123)),
                new ProductDTO(2, "test3", BigDecimal.valueOf(2.512))
        );
    }
}
