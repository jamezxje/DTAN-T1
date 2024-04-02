package com.capstone.app.service.dash;

import com.capstone.app.entity.Size;
import com.capstone.app.entity.dto.front.response.SizeItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SizeService {

    List<Size> findAllSize();

    List<SizeItemResponseDTO> findAllSizeItemResponseDTO();

    Page<Size> findAllSize(Pageable pageable);

    Size findSizebySizeId(Integer colorId);

    void saveSize(Size color);
    
    void deleteSizeBySizeId(Integer colorId);

}
