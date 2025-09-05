package com.example.module3_exam.service;

import com.example.module3_exam.dto.ProductDTO;
import com.example.module3_exam.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
    void delete(String productId);
    Product findById(String productId);
    void update(String id, Product product);

    // DTO methods
    List<ProductDTO> findAllDTO();
    List<ProductDTO> searchDTO(String name, String categoryId);

    // Pagination
    List<ProductDTO> findPaginatedDTO(int page, int size);
    List<ProductDTO> searchPaginatedDTO(String name, String categoryId, int page, int size);
    int countAll();
    int countSearchDTO(String name, String categoryId);
}
