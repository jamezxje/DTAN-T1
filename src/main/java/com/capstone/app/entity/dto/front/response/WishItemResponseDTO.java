package com.capstone.app.entity.dto.front.response;

import com.capstone.app.entity.Car;
import com.capstone.app.entity.WishItem;
import com.capstone.app.entity.type.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishItemResponseDTO {
    private Integer carId;
    private String imageUrl;
    private String carModel;
    private Integer numberOfSeats;
    private String carColor;
    private Double basePrice;
    private String imageAlt;
    private Status status;
}
