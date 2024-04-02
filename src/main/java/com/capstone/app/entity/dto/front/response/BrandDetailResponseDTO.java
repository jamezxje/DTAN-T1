package com.capstone.app.entity.dto.front.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDetailResponseDTO {
    private Integer brandId;
    private String brandName;
    private String brandDescription;
    private String brandImageUrl;
}
