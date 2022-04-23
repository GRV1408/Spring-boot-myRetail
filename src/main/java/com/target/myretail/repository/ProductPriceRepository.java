package com.target.myretail.repository;

import com.target.myretail.model.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductPriceRepository extends MongoRepository<ProductPrice, Integer> {
    ProductPrice getProductPriceById(int productId);
}
