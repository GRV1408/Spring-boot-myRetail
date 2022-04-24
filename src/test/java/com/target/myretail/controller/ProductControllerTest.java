package com.target.myretail.controller;

import com.target.myretail.model.Product;
import com.target.myretail.model.ProductPrice;
import com.target.myretail.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    private int productId = 1000;

    @Test
    public void productDetailsTest() throws Exception {
        Product product = new Product();
        product.setId(productId);
        product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
        product.setProductPrice(getProductPrice());
        Mockito.when(productService.getProductById(productId)).thenReturn(product);
        ResponseEntity<Product> newProduct = productController.productDetails(productId);
        assertEquals(newProduct.getBody().getId(),productId);
    }

    private ProductPrice getProductPrice() {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId(1000);
        productPrice.setPrice(BigDecimal.valueOf(14.00));
        productPrice.setCurrencyCode("USD");
        return productPrice;
    }
}
