package com.example.module3_exam.model;

import java.time.LocalDate;

public class Product {
    private String productId;
    private String productName;
    private String unit;
    private int price;
    private Category category;
    private LocalDate harvestDate;

    public Product() {}

    public Product(String productId, String productName, String unit, int price,
                   Category category, LocalDate harvestDate) {
        this.productId = productId;
        this.productName = productName;
        this.unit = unit;
        this.price = price;
        this.category = category;
        this.harvestDate = harvestDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", harvestDate=" + harvestDate +
                '}';
    }
}

