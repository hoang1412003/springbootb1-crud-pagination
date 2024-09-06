package com.example.buoi1_springboot.controllers;

import com.example.buoi1_springboot.dtos.CategoryDTO;
import com.example.buoi1_springboot.models.Category;
import com.example.buoi1_springboot.responses.CategoryListRespose;
import com.example.buoi1_springboot.responses.CategoryResponse;
import com.example.buoi1_springboot.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category create(@RequestBody CategoryDTO categoryDTO){
        return categoryService.saveCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    public Category edit(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(id, categoryDTO);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "Successfully Delete with id: " + id;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> postCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors);
        }

        categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok("insert" + categoryDTO);
    }
    @PostMapping("/insert1")
    public String postCategory1(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return errors.toString();
        }
        categoryService.saveCategory(categoryDTO);
        return "insert1" + categoryDTO;
    }

    @GetMapping("/list")
    public ResponseEntity<CategoryListRespose> getAllCategories(@RequestParam("page") int page,
                                                                @RequestParam("limit") int limit) {
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("createdAt").descending()
        );
        Page<CategoryResponse> categoryResponsePage = categoryService.getAllCategories(pageRequest);
        // CategoryResponsePage chứa thông tin cua page thứ mấy, và tổng số lượng page
        int totalPages = categoryResponsePage.getTotalPages();
        List<CategoryResponse> responseCategories = categoryResponsePage.getContent();
        return ResponseEntity.ok(
                CategoryListRespose.builder()
                    .categories(responseCategories)
                    .totalPages(totalPages)
                    .build()
        );
    }
}
