package com.capstone.app.entity.dto.front.response;

import com.capstone.app.entity.type.FuelType;
import com.capstone.app.entity.type.TransmissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDetailResponseDTO {

    private Integer carId;
    private String carModel;
    private String featuredImageUrl;
    private Integer brandId;
    private String brandName;
    private Double basePrice;
    private String carColor;
    private FuelType fuelType;
    private String fuelConsumption;
    private Integer numberOfSeats;
    private String carLicensePlate;
    private Integer productionYear;
    private TransmissionType transmissionType;
    private Integer mileage;
    private Double deposit;
    private String address;
    private String description;
    private String additionalFunction;
    private String termOfUse;
    private boolean isCarAvailableToBooking = false;
    private boolean isInWishlist;
    private List<String> images;

    private Integer totalRating;
    private Double averageRating;

    public CarDetailResponseDTO(Integer carId, String carModel, String featuredImageUrl, Integer brandId, String brandName, Double basePrice, String carColor, FuelType fuelType, String fuelConsumption, Integer numberOfSeats, String carLicensePlate, Integer productionYear, TransmissionType transmissionType, Integer mileage, Double deposit, String address, String description, String additionalFunction, String termOfUse) {
        this.carId = carId;
        this.carModel = carModel;
        this.featuredImageUrl = featuredImageUrl;
        this.brandId = brandId;
        this.brandName = brandName;
        this.basePrice = basePrice;
        this.carColor = carColor;
        this.fuelType = fuelType;
        this.fuelConsumption = fuelConsumption;
        this.numberOfSeats = numberOfSeats;
        this.carLicensePlate = carLicensePlate;
        this.productionYear = productionYear;
        this.transmissionType = transmissionType;
        this.mileage = mileage;
        this.deposit = deposit;
        this.address = address;
        this.description = description;
        this.additionalFunction = additionalFunction;
        this.termOfUse = termOfUse;
    }

    public CarDetailResponseDTO(Integer carId, String carModel, String featuredImageUrl,
                                Integer brandId, String brandName, Double basePrice, String carColor,
                                FuelType fuelType, String fuelConsumption, Integer numberOfSeats,
                                String carLicensePlate, Integer productionYear, TransmissionType transmissionType,
                                Integer mileage, Double deposit, String address, String description,
                                String additionalFunction, String termOfUse,
                                Integer totalRating, Double averageRating) {
        this.carId = carId;
        this.carModel = carModel;
        this.featuredImageUrl = featuredImageUrl;
        this.brandId = brandId;
        this.brandName = brandName;
        this.basePrice = basePrice;
        this.carColor = carColor;
        this.fuelType = fuelType;
        this.fuelConsumption = fuelConsumption;
        this.numberOfSeats = numberOfSeats;
        this.carLicensePlate = carLicensePlate;
        this.productionYear = productionYear;
        this.transmissionType = transmissionType;
        this.mileage = mileage;
        this.deposit = deposit;
        this.address = address;
        this.description = description;
        this.additionalFunction = additionalFunction;
        this.termOfUse = termOfUse;
        this.averageRating = averageRating;
        this.totalRating = totalRating;
    }
}
