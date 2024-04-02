package com.capstone.app.entity;

import com.capstone.app.entity.type.FuelType;
import com.capstone.app.entity.type.Status;
import com.capstone.app.entity.type.TransmissionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @ManyToOne
    @JoinColumn(name = "owner_id",nullable = false)
    private Member owner;

    @ManyToOne
    @JoinColumn(name = "brand_id",nullable = false)
    private Brand brand;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_image_id")
    private Image featureImage;

    @OneToMany
    private List<Image> images;

    @Column(name = "car_model",nullable = false, length = 50)
    @Size(max = 50)
    @NotBlank
    private String carModel;

    @Column(name = "car_license_plate",nullable = false,length = 20)
    @Size(min = 7,max = 20)
    @NotBlank
    private String carLicensePlate;

    @Column(name = "car_color",nullable = false,length = 20)
    @Size(max = 20)
    @NotBlank
    private String carColor;

    @Column(name = "number_of_seats",nullable = false, length = 2)
    @Max(99)
    @Min(4)
    private Integer numberOfSeats;

    @Column(name = "production_year",nullable = false, length = 4)
    @Min(1900)
    private Integer productionYear;

    @Column(name = "transmission_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @Column(name = "fuel_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "mileage",nullable = false,length = 10)
    @Min(0)
    private Integer mileage;

    @Column(name = "fuel_consumption",nullable = false,length = 10)
    private String fuelConsumption;

    @Column(name = "base_price",nullable = false)
    private Double basePrice;

    @Column(name = "deposit",nullable = false)
    private Double deposit;

    @Column(name = "address",nullable = false,length = 100)
    @Size(max = 100)
    private String address;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "additional_function")
    private String additionalFunction;

    @Column(name = "term_of_use", columnDefinition = "text")
    private String termOfUse;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted; // default false

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

}
