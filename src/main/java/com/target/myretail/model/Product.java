package com.target.myretail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = -8331537878236316445L;

    private int id;
    private String name;
    @JsonProperty(value = "current_price")
    private ProductPrice productPrice;

    public Product() {
    }

    public Product(int id, String name, ProductPrice productPrice) {
        this.id = id;
        this.name = name;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
