package com.target.myretail.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "productInfo",url = "${redskyproduct.ribbon.listOfServers}")
public interface ProductClient {
    @GetMapping(value = "/redsky_aggregations/v1/redsky/case_study_v1?key=3yUxt7WltYG7MFKPp7uyELi1K40ad2ys&tcin={productId}", produces = "application/json")
    String getProductInfoById(@RequestParam("productId") int productId);
}
