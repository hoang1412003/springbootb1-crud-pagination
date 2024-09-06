package com.example.buoi1_springboot.repositories;

import com.example.buoi1_springboot.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
