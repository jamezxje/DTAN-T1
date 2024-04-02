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
public class ProductDetailResponseDTO {
    private Integer productId;
    private String productName;
    private String featuredImageUrl;
    private Integer categoryId;
    private String categoryName;
    private Integer colorId;
    private String colorName;
    private Integer sizeId;
    private String sizeName;
    private Integer quantity;
    private Double basePrice;
    private Double deposit;
    private String description;
    private boolean isCarAvailableToBooking = false;
    private boolean isInWishlist;
    private List<String> images;

    private Integer totalRating;
    private Double averageRating;

    public ProductDetailResponseDTO(Integer productId, String productName, String featuredImageUrl, Integer categoryId, String categoryName, Integer colorId, String colorName, Integer sizeId, String sizeName, Integer quantity, Double basePrice, Double deposit, String description) {
        this.productId = productId;
        this.productName = productName;
        this.featuredImageUrl = featuredImageUrl;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.colorId = colorId;
        this.colorName = colorName;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.quantity = quantity;
        this.basePrice = basePrice;
        this.deposit = deposit;
        this.description = description;
    }

    //    public ProductDetailResponseDTO(Integer productId, String carModel, String featuredImageUrl, Integer brandId, String brandName, Double basePrice, String carColor, FuelType fuelType, String fuelConsumption, Integer numberOfSeats, String carLicensePlate, Integer productionYear, TransmissionType transmissionType, Integer mileage, Double deposit, String address, String description, String additionalFunction, String termOfUse) {
//        this.carId = carId;
//        this.carModel = carModel;
//        this.featuredImageUrl = featuredImageUrl;
//        this.brandId = brandId;
//        this.brandName = brandName;
//        this.basePrice = basePrice;
//        this.carColor = carColor;
//        this.fuelType = fuelType;
//        this.fuelConsumption = fuelConsumption;
//        this.numberOfSeats = numberOfSeats;
//        this.carLicensePlate = carLicensePlate;
//        this.productionYear = productionYear;
//        this.transmissionType = transmissionType;
//        this.mileage = mileage;
//        this.deposit = deposit;
//        this.address = address;
//        this.description = description;
//        this.additionalFunction = additionalFunction;
//        this.termOfUse = termOfUse;
//    }

//    public CarDetailResponseDTO(Integer carId, String carModel, String featuredImageUrl,
//                                Integer brandId, String brandName, Double basePrice, String carColor,
//                                FuelType fuelType, String fuelConsumption, Integer numberOfSeats,
//                                String carLicensePlate, Integer productionYear, TransmissionType transmissionType,
//                                Integer mileage, Double deposit, String address, String description,
//                                String additionalFunction, String termOfUse,
//                                Integer totalRating, Double averageRating) {
//        this.carId = carId;
//        this.carModel = carModel;
//        this.featuredImageUrl = featuredImageUrl;
//        this.brandId = brandId;
//        this.brandName = brandName;
//        this.basePrice = basePrice;
//        this.carColor = carColor;
//        this.fuelType = fuelType;
//        this.fuelConsumption = fuelConsumption;
//        this.numberOfSeats = numberOfSeats;
//        this.carLicensePlate = carLicensePlate;
//        this.productionYear = productionYear;
//        this.transmissionType = transmissionType;
//        this.mileage = mileage;
//        this.deposit = deposit;
//        this.address = address;
//        this.description = description;
//        this.additionalFunction = additionalFunction;
//        this.termOfUse = termOfUse;
//        this.averageRating = averageRating;
//        this.totalRating = totalRating;
//    }
}
