package com.capstone.app.entity.dto.common;

import com.capstone.app.entity.type.FuelType;
import com.capstone.app.entity.type.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarItemDTO {
    private Integer carId;
    private String carModel;
    private String brandName;
    private String featureImageUrl;
    private String carLicensePlate;
    private String ownerName;
    private Status status;
    private Integer productionYear;
    private Double basePrice;
}
