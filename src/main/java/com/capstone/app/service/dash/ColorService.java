package com.capstone.app.service.dash;

import com.capstone.app.entity.Color;
import com.capstone.app.entity.dto.front.response.ColorItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColorService {

    List<Color> findAllColor();

    List<ColorItemResponseDTO> findAllColorItemResponseDTO();

    Page<Color> findAllColor(Pageable pageable);

    Color findColorbyColorId(Integer colorId);

    void saveColor(Color color);

    void deleteColorByColorId(Integer colorId);

}
