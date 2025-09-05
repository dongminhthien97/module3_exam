package com.example.module3_exam.service;



import com.example.module3_exam.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(int id);
}
