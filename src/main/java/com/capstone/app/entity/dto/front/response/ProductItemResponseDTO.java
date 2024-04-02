package com.capstone.app.entity.dto.front.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemResponseDTO {
    private Integer productId;
    private String productName;
    private String featuredImageUrl;
    private String productSize;
    private Integer productQuantity;
    private Double basePrice;
}
