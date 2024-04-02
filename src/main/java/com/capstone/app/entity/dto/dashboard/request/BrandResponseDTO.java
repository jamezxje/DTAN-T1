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
public class BrandResponseDTO {

    private Integer brandId;
    private String brandLogoUrl;
    private String brandName;
    private String brandDescription;
    private Image brandLogo;

}
