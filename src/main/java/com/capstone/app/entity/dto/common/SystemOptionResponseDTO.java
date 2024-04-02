package com.capstone.app.entity.dto.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemOptionResponseDTO {

    private Integer optionId;
    private String optionAliasName;
    private String optionkey;
    private String optionValue;


}
