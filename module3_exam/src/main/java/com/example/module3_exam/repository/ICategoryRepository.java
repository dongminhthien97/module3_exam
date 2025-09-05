package com.example.module3_exam.repository;



import com.example.module3_exam.model.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> findAll();
    Category findById(int id);
}

