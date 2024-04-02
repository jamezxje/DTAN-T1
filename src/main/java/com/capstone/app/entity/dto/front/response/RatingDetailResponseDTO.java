package com.capstone.app.entity.dto.front.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDetailResponseDTO {
    private Integer feedbackId;
    private String feedbackContent;
    private Integer feedbackPoint;
    private LocalDateTime feedbackDate;
    private String memberName;
}
