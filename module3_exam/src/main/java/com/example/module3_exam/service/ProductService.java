package com.example.module3_exam.service;

import com.example.module3_exam.model.Product;
import com.example.module3_exam.repository.IProductRepository;
import com.example.module3_exam.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private final IProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        if (product.getPrice() < 100) {
            throw new IllegalArgumentException("Giá sản phẩm phải >= 100 VNĐ");
        }
        productRepository.save(product);
    }

}
