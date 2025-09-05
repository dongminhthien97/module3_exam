package com.example.module3_exam.service;

import com.example.module3_exam.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
}
