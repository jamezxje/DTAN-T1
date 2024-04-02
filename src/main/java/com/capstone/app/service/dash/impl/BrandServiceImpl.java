package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Brand;
import com.capstone.app.entity.Image;
import com.capstone.app.entity.dto.dashboard.request.BrandRequestDTO;
import com.capstone.app.entity.dto.dashboard.request.BrandResponseDTO;
import com.capstone.app.entity.dto.front.response.BrandDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.BrandItemResponseDTO;
import com.capstone.app.repository.BrandRepo;
import com.capstone.app.service.common.FilesStorageService;
import com.capstone.app.service.dash.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepo brandRepo;
    private final FilesStorageService filesStorageService;

    @Override
    public List<BrandItemResponseDTO> findAllBrandItemResponseDTO() {
        return brandRepo.findAllBrandItemResponseDTO();
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepo.findAll();
    }

    @Override
    public Page<BrandResponseDTO> findAllBrandResponseDTO(Pageable pageable) {
        return brandRepo.findAllBrandResponseDTO(pageable);
    }

    @Override
    public void saveBrand(BrandRequestDTO request) {
        Brand brand = request.toEntity();
        Image logo = filesStorageService.save(request.getBrandLogo());
        brand.setBrandLogo(logo);
        brandRepo.save(brand);
    }

    @Override
    public void editBrand(BrandRequestDTO request) {
        Brand brand = brandRepo.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        request.updateEntity(brand);

        if (request.getDeleteBrandLogo() != null && request.getDeleteBrandLogo()) {
            filesStorageService.delete(brand.getBrandLogo());
            brand.setBrandLogo(null);
        }

        if (request.getBrandLogo() != null && !request.getBrandLogo().isEmpty()) {
            Image brandLogo = filesStorageService.save(request.getBrandLogo());
            brand.setBrandLogo(brandLogo);
        }

        brandRepo.save(brand);
    }

    @Override
    public BrandRequestDTO findBrandDTOById(Integer brandId) {
        Brand brand = brandRepo.findById(brandId).orElseThrow(() -> new RuntimeException("Brand not found"));
        return BrandRequestDTO.toDTO(brand);
    }

    @Override
    public Brand findBrandById(Integer brandId) {
        return brandRepo.findById(brandId).orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    @Override
    public BrandItemResponseDTO findBrandItemResponseDTOById(Integer id) {
        return brandRepo.findBrandItemResponseDTOById(id);
    }

    @Override
    public BrandDetailResponseDTO findBrandDetailResponseDTOById(Integer id) {
        return brandRepo.findBrandDetailResponseDTOById(id);
    }

    @Override
    public boolean deleteBrandById(Integer brandId) {
        this.brandRepo.deleteById(brandId);
        return (brandRepo.findById(brandId).isEmpty());
    }
}
