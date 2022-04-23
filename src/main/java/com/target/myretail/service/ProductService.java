package com.target.myretail.service;

import com.target.myretail.model.Product;

public interface ProductService {
    Product getProductById(int productId) throws Exception;
    void updateProductPrice(int productId, Product product) throws Exception;
}
