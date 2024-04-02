package com.capstone.app.entity.dto.front.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetailResponseDTO {
    private Integer categoryId;
    private String categorySizeChartUrl;
    private String categoryName;
    private String categoryDescription;
}
