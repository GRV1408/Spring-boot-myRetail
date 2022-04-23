package com.target.myretail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.client.ProductClient;
import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.model.Product;
import com.target.myretail.model.ProductPrice;
import com.target.myretail.repository.ProductPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private ProductClient productClient;

    public Product getProductById(int productId) throws Exception{
        logger.info("Entered in getProductById - productId = {}", productId);
        long startTime = System.currentTimeMillis();
        ProductPrice productPrice = productPriceRepository.getProductPriceById(productId);
        String productName = getProductName(productId);
        Product prodDetails= new Product(productId, productName, productPrice);
        logger.info("Product details for - productId {} -> {}", productId, prodDetails);
        long endTime = System.currentTimeMillis();
        logger.info("Time taken in getProductById in ms {}", (endTime - startTime));
        return prodDetails;
    }

    private String getProductName(int productId) {
       String title = "";
        try {
            String productInfo=productClient.getProductInfoById(productId);
            if(StringUtils.hasLength(productInfo)){
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode node = objectMapper.readTree(productInfo);
                List<String> titleList = node.findValuesAsText("title");
                if (!titleList.isEmpty()) {
                    title = titleList.get(0);
                }
            }
        } catch (JsonProcessingException e) {
            logger.error("Exception while fetching the ProductInfo {}",e.getMessage());
        }
        return title;
    }

    public void updateProductPrice(int productId, Product product) throws Exception {
        logger.info("Entered in updateProductById - productId = {}", productId);
        long startTime = System.currentTimeMillis();
        ProductPrice productPrice = productPriceRepository.getProductPriceById(productId);
        if(productPrice == null){
            throw new ProductNotFoundException();
        }
        if (product.getProductPrice() != null) {
            productPrice.setPrice(product.getProductPrice().getPrice());
            productPrice.setCurrencyCode(product.getProductPrice().getCurrencyCode());
            productPriceRepository.save(productPrice);
        }
        long endTime = System.currentTimeMillis();
        logger.info("Time taken in updateProductById in ms {}", (endTime - startTime));
    }
}
