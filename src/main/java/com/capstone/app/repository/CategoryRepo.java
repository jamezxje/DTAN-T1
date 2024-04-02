package com.capstone.app.repository;

import com.capstone.app.entity.Category;
import com.capstone.app.entity.dto.dashboard.request.CategoryResponseDTO;
import com.capstone.app.entity.dto.front.response.CategoryDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.CategoryItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CategoryItemResponseDTO(c.categoryId, c.categoryName) FROM Category c")
    List<CategoryItemResponseDTO> findAllCategoryItemResponseDTO();

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CategoryDetailResponseDTO(c.categoryId, c.categoryName, c.categoryDescription, c.categorySizeChart.imageUrl) FROM Category c WHERE c.categoryId = :categoryId")
    CategoryDetailResponseDTO findCategoryDetailResponseDTOById(@Param("categoryId") Integer categoryId);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CategoryItemResponseDTO(c.categoryId, c.categoryName) FROM Category c WHERE c.categoryId = :categoryId")
    CategoryItemResponseDTO findCategoryItemResponseDTOById(@Param("categoryId") Integer categoryId);

    @Query("SELECT new com.capstone.app.entity.dto.dashboard.request.CategoryResponseDTO(c.categoryId, c.categorySizeChart.imageUrl, c.categorySizeChart, c.categoryName, c.categoryDescription) FROM Category c LEFT JOIN c.categorySizeChart")
    Page<CategoryResponseDTO> findAllCategoryResponseDTO(Pageable pageable);

}
