package com.capstone.app.service.dash;

import com.capstone.app.entity.Brand;
import com.capstone.app.entity.dto.dashboard.request.BrandRequestDTO;
import com.capstone.app.entity.dto.dashboard.request.BrandResponseDTO;
import com.capstone.app.entity.dto.front.response.BrandDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.BrandItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrand();

    Page<BrandResponseDTO> findAllBrandResponseDTO(Pageable pageable);

    List<BrandItemResponseDTO> findAllBrandItemResponseDTO();

    void saveBrand(BrandRequestDTO request);

    void editBrand(BrandRequestDTO request);

    BrandRequestDTO findBrandDTOById(Integer brandId);

    Brand findBrandById(Integer brandId);

    BrandItemResponseDTO findBrandItemResponseDTOById(Integer id);

    BrandDetailResponseDTO findBrandDetailResponseDTOById(Integer id);

    boolean deleteBrandById(Integer brandId);
}
