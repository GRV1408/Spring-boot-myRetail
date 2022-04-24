package com.target.myretail.service;

import com.target.myretail.client.ProductClient;
import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.model.Product;
import com.target.myretail.model.ProductPrice;
import com.target.myretail.repository.ProductPriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductPriceRepository ProductPriceRepository;

    @Mock
    ProductClient productClient;

    private int productId = 1000;

    @Test
    public void getProductByIdTest () throws Exception {
        ProductPrice productPrice = getProductPrice();
        Mockito.when(ProductPriceRepository.getProductPriceById(productId)).thenReturn(productPrice);
        Mockito.when(productClient.getProductInfoById(productId)).thenReturn(getProductInfo());
        Product product = productService.getProductById(productId);
        assertEquals(product.getId(),productId);
    }

    private ProductPrice getProductPrice() {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId(1000);
        productPrice.setPrice(BigDecimal.valueOf(14.00));
        productPrice.setCurrencyCode("USD");
        return productPrice;
    }

    @Test
    public void updateProductPriceTest () throws Exception {
        Product product = new Product();
        product.setId(productId);
        product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
        product.setProductPrice(getProductPrice());
        Mockito.when(ProductPriceRepository.getProductPriceById(productId)).thenReturn(getProductPrice());
        Mockito.when(ProductPriceRepository.save(any(ProductPrice.class))).thenReturn(getProductPrice());
        productService.updateProductPrice(productId, product);
    }

    @Test
    public void updateProductPriceProductNotFoundExceptionTest () throws Exception {
        Product product = new Product();
        product.setId(productId);
        product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
        product.setProductPrice(getProductPrice());
        Mockito.when(ProductPriceRepository.getProductPriceById(productId)).thenReturn(null);
        Exception thrown = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProductPrice(productId, product);
        });
        assertTrue(thrown instanceof ProductNotFoundException);

    }

    private String getProductInfo () {
        return "{\n" +
                "  \"data\": {\n" +
                "    \"product\": {\n" +
                "      \"tcin\": \"13860428\",\n" +
                "      \"item\": {\n" +
                "        \"product_description\": {\n" +
                "          \"title\": \"The Big Lebowski (Blu-ray)\",\n" +
                "          \"downstream_description\": \"Jeff \\\"The Dude\\\" Lebowski (Bridges) is the victim of mistaken identity. Thugs break into his apartment in the errant belief that they are accosting Jeff Lebowski, the eccentric millionaire philanthropist, not the laid-back, unemployed Jeff Lebowski. In the aftermath, \\\"The Dude\\\" seeks restitution from his wealthy namesake. He and his buddies (Goodman and Buscemi) are swept up in a kidnapping plot that quickly spins out of control.\"\n" +
                "        },\n" +
                "        \"enrichment\": {\n" +
                "          \"images\": {\n" +
                "            \"primary_image_url\": \"https://target.scene7.com/is/image/Target/GUEST_bac49778-a5c7-4914-8fbe-96e9cd549450\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"product_classification\": {\n" +
                "          \"product_type_name\": \"ELECTRONICS\",\n" +
                "          \"merchandise_type_name\": \"Movies\"\n" +
                "        },\n" +
                "        \"primary_brand\": {\n" +
                "          \"name\": \"Universal Home Video\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }
}
