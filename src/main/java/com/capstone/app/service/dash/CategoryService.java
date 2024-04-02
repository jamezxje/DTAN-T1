package com.capstone.app.service.dash;

import com.capstone.app.entity.Category;
import com.capstone.app.entity.dto.dashboard.request.CategoryRequestDTO;
import com.capstone.app.entity.dto.dashboard.request.CategoryResponseDTO;
import com.capstone.app.entity.dto.front.response.CategoryDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.CategoryItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory();

    List<CategoryItemResponseDTO> findAllCategoryItemResponseDTO();

    Page<CategoryResponseDTO> findAllCategoryResponseDTO(Pageable pageable);

    void saveCategory(CategoryRequestDTO request);

    void editCategory(CategoryRequestDTO request);

    CategoryRequestDTO findCategoryDTOById(Integer CategoryId);

    Category findCategoryById(Integer CategoryId);

    CategoryItemResponseDTO findCategoryItemResponseDTOById(Integer id);

    CategoryDetailResponseDTO findCategoryDetailResponseDTOById(Integer id);

    boolean deleteCategoryById(Integer CategoryId);
}
