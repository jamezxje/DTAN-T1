package com.capstone.app.repository;

import com.capstone.app.entity.Color;
import com.capstone.app.entity.dto.front.response.ColorItemResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepo extends JpaRepository<Color, Integer> {

    @Query("SELECT new com.capstone.app.entity.dto.front.response.ColorItemResponseDTO(c.colorId, c.colorName) FROM Color c")
    List<ColorItemResponseDTO> findAllColorItemResponseDTO();

}
