package com.example.module3_exam.dto;

import java.time.LocalDate;

public class ProductDTO {
    private String productId;
    private String productName;
    private String unit;
    private int price;
    private LocalDate harvestDate;
    private int categoryId;
    private String categoryName;

    public ProductDTO() {}

    public ProductDTO(String productId, String productName, String unit, int price,
                      LocalDate harvestDate, int categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.unit = unit;
        this.price = price;
        this.harvestDate = harvestDate;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Getter - Setter
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

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

