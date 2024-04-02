package com.capstone.app.entity.dto.dashboard.request;

import com.capstone.app.entity.Car;
import com.capstone.app.entity.Image;
import com.capstone.app.entity.type.Status;
import com.capstone.app.entity.type.FuelType;
import com.capstone.app.entity.type.TransmissionType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequestDTO {

    private Integer carId;
    private Integer ownerId;
    private Integer brandId;
    private String brandName;
    private MultipartFile featureImage;
    private String featureImageUrl;
    private MultipartFile[] images;
    private List<String> imageUrls;
    private List<String> deleteImagesUrl;
    private String carModel;
    private String carLicensePlate;
    private String carColor;
    private Integer numberOfSeats;
    private Integer productionYear;
    private TransmissionType transmissionType;
    private FuelType fuelType;
    private Integer mileage;
    private String fuelConsumption;
    private Double basePrice;
    private Double deposit;
    private String address;
    private String description;
    private String additionalFunction;
    private String termOfUse;
    private Status status;

    public Car toEntity() {
        return Car.builder()
                .carModel(carModel)
                .carColor(carColor)
                .numberOfSeats(numberOfSeats)
                .productionYear(productionYear)
                .transmissionType(transmissionType)
                .carLicensePlate(carLicensePlate)
                .fuelType(fuelType)
                .mileage(mileage)
                .fuelConsumption(fuelConsumption)
                .basePrice(basePrice)
                .deposit(deposit)
                .address(address)
                .description(description)
                .additionalFunction(additionalFunction)
                .termOfUse(termOfUse)
                .status(Status.PENDING)
                .build();
    }

    public static CarRequestDTO toDTO(Car car) {
        List<String> imageUrls = car.getImages().stream().map(Image::getImageUrl).toList();
        return CarRequestDTO.builder()
                .carId(car.getCarId())
                .brandId(car.getBrand().getBrandId())
                .carModel(car.getCarModel())
                .carColor(car.getCarColor())
                .featureImageUrl(car.getFeatureImage().getImageUrl())
                .imageUrls(imageUrls)
                .numberOfSeats(car.getNumberOfSeats())
                .productionYear(car.getProductionYear())
                .transmissionType(car.getTransmissionType())
                .carLicensePlate(car.getCarLicensePlate())
                .fuelType(car.getFuelType())
                .mileage(car.getMileage())
                .fuelConsumption(car.getFuelConsumption())
                .basePrice(car.getBasePrice())
                .deposit(car.getDeposit())
                .address(car.getAddress())
                .description(car.getDescription())
                .additionalFunction(car.getAdditionalFunction())
                .termOfUse(car.getTermOfUse())
                .brandName(car.getBrand().getBrandName())
                .status(car.getStatus())
                .build();
    }

    public void updateEntity(Car car) {
        car.setCarModel(carModel);
        car.setCarColor(carColor);
        car.setNumberOfSeats(numberOfSeats);
        car.setProductionYear(productionYear);
        car.setTransmissionType(transmissionType);
        car.setCarLicensePlate(carLicensePlate);
        car.setFuelType(fuelType);
        car.setMileage(mileage);
        car.setFuelConsumption(fuelConsumption);
        car.setBasePrice(basePrice);
        car.setDeposit(deposit);
        car.setAddress(address);
        car.setDescription(description);
        car.setAdditionalFunction(additionalFunction);
        car.setTermOfUse(termOfUse);
    }
}
