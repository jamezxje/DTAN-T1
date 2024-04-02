package com.capstone.app.entity.dto.dashboard.request;

import com.capstone.app.entity.Brand;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandRequestDTO {

    private Integer brandId;
    private String brandLogoUrl;
    private MultipartFile brandLogo;
    private String brandName;
    private String brandDescription;
    private Boolean deleteBrandLogo;

    public Brand toEntity() {
        return Brand.builder()
                .brandName(brandName)
                .brandDescription(brandDescription)
                .build();
    }

    public static BrandRequestDTO toDTO(Brand brand) {
        if (brand.getBrandLogo() != null) {
            return BrandRequestDTO.builder()
                    .brandId(brand.getBrandId())
                    .brandLogoUrl(brand.getBrandLogo().getImageUrl())
                    .brandName(brand.getBrandName())
                    .brandDescription(brand.getBrandDescription())
                    .build();
        } else {
            return BrandRequestDTO.builder()
                    .brandId(brand.getBrandId())
                    .brandName(brand.getBrandName())
                    .brandDescription(brand.getBrandDescription())
                    .build();
        }
    }

    public void updateEntity(Brand brand) {
        brand.setBrandName(brandName);
        brand.setBrandDescription(brandDescription);
    }

}
