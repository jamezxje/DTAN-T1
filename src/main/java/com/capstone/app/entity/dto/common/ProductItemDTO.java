package com.capstone.app.entity.dto.common;

import com.capstone.app.entity.type.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductItemDTO {
    private Integer productId;
    private String productName;
    private String categoryName;
    private String colorName;
    private String sizeName;
    private String featureImageUrl;
    private Status status;
    private Double basePrice;
    private Integer quantity;
}
