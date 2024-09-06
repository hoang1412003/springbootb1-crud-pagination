package com.example.buoi1_springboot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDTO {
    @JsonProperty("name")
    @NotEmpty(message = "Tên không được rỗng")
    private String categoryName;
}
