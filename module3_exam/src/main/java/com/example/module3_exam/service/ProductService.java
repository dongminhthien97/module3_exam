package com.example.module3_exam.service;

import com.example.module3_exam.dto.ProductDTO;
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
    public List<ProductDTO> findAllDTO() {
        return productRepository.findAllDTO();
    }

    @Override
    public void save(Product product) {
        // Có thể thêm validate ở đây trước khi gọi repo
        if (product.getPrice() < 1000) {
            throw new IllegalArgumentException("Giá sản phẩm phải >= 1000 VNĐ");
        }
        productRepository.save(product);
    }

    @Override
    public void delete(String productId) {
        productRepository.delete(productId);
    }

    @Override
    public Product findById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void update(String id, Product product) {
        productRepository.update(id, product);
    }

    @Override
    public List<ProductDTO> searchDTO(String name, String categoryId) {
        return productRepository.searchDTO(name, categoryId);
    }

    @Override
    public List<ProductDTO> findPaginatedDTO(int page, int size) {
        return productRepository.findPaginatedDTO(page, size);
    }

    @Override
    public List<ProductDTO> searchPaginatedDTO(String name, String categoryId, int page, int size) {
        return productRepository.searchPaginatedDTO(name, categoryId, page, size);
    }

    @Override
    public int countAll() {
        return productRepository.countAll();
    }

    @Override
    public int countSearchDTO(String name, String categoryId) {
        return productRepository.countSearchDTO(name, categoryId);
    }
}
