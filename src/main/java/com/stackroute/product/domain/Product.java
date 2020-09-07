package com.stackroute.product.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private int prodId;
    private String prodName;
    private String category;

    public Product(int prodId, String prodName, String category) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.category = category;
    }

    public Product() {
    }

    public int getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public String getCategory() {
        return category;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
