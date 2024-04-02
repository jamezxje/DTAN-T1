package com.capstone.app.service.common;

import com.capstone.app.entity.dto.front.response.BrandItemResponseDTO;
import com.capstone.app.entity.dto.front.response.CategoryItemResponseDTO;
import com.capstone.app.entity.dto.front.response.ColorItemResponseDTO;
import com.capstone.app.entity.dto.front.response.SizeItemResponseDTO;
import com.capstone.app.service.dash.*;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommonDataService {

    @Getter
    private List<BrandItemResponseDTO> brands;

    @Getter
    private List<CategoryItemResponseDTO> categories;

    @Getter
    private List<ColorItemResponseDTO> colors;

    @Getter
    private List<SizeItemResponseDTO> sizes;

    private final BrandService brandService;

    private final CategoryService categoryService;

    private final ColorService colorService;

    private final SizeService sizeService;

    @PostConstruct
    public void init() {
        brands = brandService.findAllBrandItemResponseDTO();
        categories = categoryService.findAllCategoryItemResponseDTO();
        colors = colorService.findAllColorItemResponseDTO();
        sizes = sizeService.findAllSizeItemResponseDTO();
    }


    public void refreshBrands() {
        brands = brandService.findAllBrandItemResponseDTO();
    }

    public void refreshCategories() {
        categories = categoryService.findAllCategoryItemResponseDTO();
    }

}
