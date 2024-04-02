package com.capstone.app.repository;

import com.capstone.app.entity.Brand;
import com.capstone.app.entity.dto.dashboard.request.BrandResponseDTO;
import com.capstone.app.entity.dto.front.response.BrandDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.BrandItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {
    @Query("SELECT new com.capstone.app.entity.dto.front.response.BrandItemResponseDTO(b.brandId, b.brandName) FROM Brand b")
    List<BrandItemResponseDTO> findAllBrandItemResponseDTO();

    @Query("SELECT new com.capstone.app.entity.dto.front.response.BrandDetailResponseDTO(b.brandId, b.brandName, b.brandDescription, b.brandLogo.imageUrl) FROM Brand b WHERE b.brandId = :brandId")
    BrandDetailResponseDTO findBrandDetailResponseDTOById(@Param("brandId") Integer brandId);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.BrandItemResponseDTO(b.brandId, b.brandName) FROM Brand b WHERE b.brandId = :brandId")
    BrandItemResponseDTO findBrandItemResponseDTOById(@Param("brandId") Integer brandId);

    @Query("SELECT new com.capstone.app.entity.dto.dashboard.request.BrandResponseDTO(b.brandId, b.brandLogo.imageUrl, b.brandName, b.brandDescription, b.brandLogo) FROM Brand b LEFT JOIN b.brandLogo")
//    List<BrandResponseDTO> findAllBrandResponseDTO();
    Page<BrandResponseDTO> findAllBrandResponseDTO(Pageable pageable);

}
