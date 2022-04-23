package com.target.myretail.controller;

import com.target.myretail.vo.CustomResponse;
import com.target.myretail.exception.ProductMisMatchException;
import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.model.Product;
import com.target.myretail.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.target.myretail.vo.CustomResponse.Status.SUCCESS;

@RequestMapping(value="/products")
@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> productDetails(@PathVariable("id") int productId) {
        logger.info("Entered in productDetails - productId = {}", productId);
        Product product = null;
        try {
            product = productService.getProductById(productId);
        } catch (Exception e) {
            logger.error("Error while getting the Product details - {}", e.getMessage());
            throw new ProductNotFoundException();
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

   @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updatePrice(@RequestBody Product product, @PathVariable("id") int productId) {
       logger.info("Entered in updatePrice for productId = {}", productId);
       if (product.getId() != productId) {
           throw new ProductMisMatchException();
       }
       try {
           productService.updateProductPrice(productId, product);
       } catch (Exception e) {
           logger.error("Error occur while updating the Product price - {}", e.getMessage());
           throw new ProductNotFoundException();
       }
        return new ResponseEntity<>(
                new CustomResponse(SUCCESS, "Product price has been updated"), HttpStatus.OK);
    }
}
