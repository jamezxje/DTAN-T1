package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Category;
import com.capstone.app.entity.Image;
import com.capstone.app.entity.dto.dashboard.request.CategoryRequestDTO;
import com.capstone.app.entity.dto.dashboard.request.CategoryResponseDTO;
import com.capstone.app.entity.dto.front.response.CategoryDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.CategoryItemResponseDTO;
import com.capstone.app.repository.CategoryRepo;
import com.capstone.app.service.common.FilesStorageService;
import com.capstone.app.service.dash.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final FilesStorageService filesStorageService;

    @Override
    public List<CategoryItemResponseDTO> findAllCategoryItemResponseDTO(){
        return categoryRepo.findAllCategoryItemResponseDTO();
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Page<CategoryResponseDTO> findAllCategoryResponseDTO(Pageable pageable) {
        return categoryRepo.findAllCategoryResponseDTO(pageable);
    }

    @Override
    public void saveCategory(CategoryRequestDTO request) {
        Category category = request.toEntity();
        Image sizeChart = filesStorageService.save(request.getCategorySizeChart());
        category.setCategorySizeChart(sizeChart);
        categoryRepo.save(category);
    }

    @Override
    public void editCategory(CategoryRequestDTO request) {
        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        request.updateEntity(category);

        if (request.getDeleteSizeChart() != null && request.getDeleteSizeChart()) {
            filesStorageService.delete(category.getCategorySizeChart());
            category.setCategorySizeChart(null);
        }

        if (request.getCategorySizeChart() != null && !request.getCategorySizeChart().isEmpty()) {
            Image categorySizeChart = filesStorageService.save(request.getCategorySizeChart());
            category.setCategorySizeChart(categorySizeChart);
        }

        categoryRepo.save(category);
    }

    @Override
    public CategoryRequestDTO findCategoryDTOById(Integer CategoryId) {
        Category Category = categoryRepo.findById(CategoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryRequestDTO.toDTO(Category);
    }

    @Override
    public Category findCategoryById(Integer CategoryId) {
        return categoryRepo.findById(CategoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryItemResponseDTO findCategoryItemResponseDTOById(Integer id) {
        return categoryRepo.findCategoryItemResponseDTOById(id);
    }

    @Override
    public CategoryDetailResponseDTO findCategoryDetailResponseDTOById(Integer id) {
        return categoryRepo.findCategoryDetailResponseDTOById(id);
    }

    @Override
    public boolean deleteCategoryById(Integer CategoryId) {
        this.categoryRepo.deleteById(CategoryId);
        return (categoryRepo.findById(CategoryId).isEmpty());
    }
}
