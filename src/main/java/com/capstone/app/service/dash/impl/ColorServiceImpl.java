package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Color;
import com.capstone.app.entity.dto.front.response.ColorItemResponseDTO;
import com.capstone.app.repository.ColorRepo;
import com.capstone.app.service.dash.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepo colorRepo;

    @Override
    public List<Color> findAllColor() {
        return colorRepo.findAll();
    }

    @Override
    public List<ColorItemResponseDTO> findAllColorItemResponseDTO() {
        return colorRepo.findAllColorItemResponseDTO();
    }

    @Override
    public Page<Color> findAllColor(Pageable pageable) {
        return colorRepo.findAll(pageable);
    }

    @Override
    public Color findColorbyColorId(Integer colorId) {
        return colorRepo.findById(colorId).orElseThrow(() -> new RuntimeException("Color not found"));
    }

    @Override
    public void saveColor(Color color) {
        this.colorRepo.save(color);
    }

    @Override
    public void deleteColorByColorId(Integer colorId) {
        this.colorRepo.deleteById(colorId);
    }

}
