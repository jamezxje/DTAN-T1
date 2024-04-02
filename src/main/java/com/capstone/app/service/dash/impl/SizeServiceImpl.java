package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Size;
import com.capstone.app.entity.dto.front.response.SizeItemResponseDTO;
import com.capstone.app.repository.SizeRepo;
import com.capstone.app.service.dash.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepo sizeRepo;

    @Override
    public List<Size> findAllSize() {
        return sizeRepo.findAll();
    }

    @Override
    public List<SizeItemResponseDTO> findAllSizeItemResponseDTO() {
        return sizeRepo.findAllSizeItemResponseDTO();
    }

    @Override
    public Page<Size> findAllSize(Pageable pageable) {
        return sizeRepo.findAll(pageable);
    }

    @Override
    public Size findSizebySizeId(Integer sizeId) {
        return sizeRepo.findById(sizeId).orElseThrow(() -> new RuntimeException("Size not found"));
    }

    @Override
    public void saveSize(Size size) {
        this.sizeRepo.save(size);
    }

    @Override
    public void deleteSizeBySizeId(Integer sizeId) {
        this.sizeRepo.deleteById(sizeId);
    }

}
