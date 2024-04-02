package com.capstone.app.entity.dto.front.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarItemResponseDTO {
    private Integer carId;
    private String carModel;
    private String featuredImageUrl;
    private Double basePrice;
    private String carColor;
    private String fuelConsumption;
    private Integer numberOfSeats;
}
