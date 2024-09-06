package com.example.buoi1_springboot.services;

import com.example.buoi1_springboot.dtos.CategoryDTO;
import com.example.buoi1_springboot.models.Category;
import com.example.buoi1_springboot.responses.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category saveCategory(CategoryDTO categoryDTO);
    Category updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Page<CategoryResponse> getAllCategories(PageRequest pageRequest);
}
