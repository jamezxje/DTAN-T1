package com.capstone.app.mapper;

import com.capstone.app.entity.SystemOption;
import com.capstone.app.entity.dto.common.SystemOptionResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SystemOptionMapper {
    public SystemOptionResponseDTO toResponseDTO(SystemOption systemOption) {
        return SystemOptionResponseDTO.builder()
                .optionId(systemOption.getOptionId())
                .optionAliasName(systemOption.getOptionAliasName())
                .optionkey(systemOption.getOptionkey())
                .optionValue(systemOption.getOptionValue())
                .build();
    }

    public List<SystemOptionResponseDTO> toListDTO(List<SystemOption> optionList) {
        return optionList.stream().map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

}
