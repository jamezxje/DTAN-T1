package com.capstone.app.entity.dto.dashboard.request;

import com.capstone.app.entity.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequestDTO {

    private Integer categoryId;
    private String categorySizeChartUrl;
    private MultipartFile categorySizeChart;
    private String categoryName;
    private String categoryDescription;
    private Boolean deleteSizeChart;

    public Category toEntity() {
        return Category.builder()
                .categoryName(categoryName)
                .categoryDescription(categoryDescription)
                .build();
    }

    public static CategoryRequestDTO toDTO(Category category) {
        if (category.getCategorySizeChart() != null) {
            return CategoryRequestDTO.builder()
                    .categoryId(category.getCategoryId())
                    .categorySizeChartUrl(category.getCategorySizeChart().getImageUrl())
                    .categoryName(category.getCategoryName())
                    .categoryDescription(category.getCategoryDescription())
                    .build();
        } else {
            return CategoryRequestDTO.builder()
                    .categoryId(category.getCategoryId())
                    .categoryName(category.getCategoryName())
                    .categoryDescription(category.getCategoryDescription())
                    .build();
        }
    }

    public void updateEntity(Category category) {
        category.setCategoryName(categoryName);
        category.setCategoryDescription(categoryDescription);
    }

}
