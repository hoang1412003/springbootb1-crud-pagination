package com.example.buoi1_springboot.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryListRespose {
    private List<CategoryResponse> categories;
    private int totalPages;
}
