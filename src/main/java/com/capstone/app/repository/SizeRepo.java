package com.capstone.app.repository;

import com.capstone.app.entity.Size;
import com.capstone.app.entity.dto.front.response.SizeItemResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepo extends JpaRepository<Size, Integer> {
    @Query("SELECT new com.capstone.app.entity.dto.front.response.SizeItemResponseDTO(s.sizeId, s.sizeName) FROM Size s")
    List<SizeItemResponseDTO> findAllSizeItemResponseDTO();
}
