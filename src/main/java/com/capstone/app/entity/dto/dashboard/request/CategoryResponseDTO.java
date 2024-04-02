package com.capstone.app.entity.dto.dashboard.request;

import com.capstone.app.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Integer categoryId;
    private String categorySizeChartUrl;
    private Image categorySizeChart;
    private String categoryName;
    private String categoryDescription;


}
