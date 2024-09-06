package com.example.buoi1_springboot.services;

import com.example.buoi1_springboot.dtos.CategoryDTO;
import com.example.buoi1_springboot.models.Category;
import com.example.buoi1_springboot.repositories.CategoryRepository;
import com.example.buoi1_springboot.responses.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(CategoryDTO categoryDTO) {
//        Category category = new Category();
//        category.setName(categoryDTO.getCategoryName());
        Category category = new Category()
                .builder()
                .name(categoryDTO.getCategoryName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(id);
        if(existingCategory != null) {
            existingCategory.setName(categoryDTO.getCategoryName());
            return categoryRepository.save(existingCategory);
        }else {
            return null;
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<CategoryResponse> getAllCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest).map(category -> {
            return CategoryResponse.fromCategory(category);
        });
    }
}
