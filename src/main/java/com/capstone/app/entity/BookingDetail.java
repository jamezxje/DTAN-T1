package com.capstone.app.entity;

import com.capstone.app.entity.type.PaymentMethod;
import com.capstone.app.entity.type.PickupType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetail {
    @Id
    @Column(name = "booking_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingDetailId;

//    @OneToOne
//    private Booking booking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    @Column(name = "base_price", nullable = false)
    @Min(0)
    private Double basePrice;

    @Column(name = "extra_fee", columnDefinition = "double default 0")
    @Min(0)
    @NotNull
    private Double extraFee = 0.0;

    @Column(name = "payment_method", nullable = false, columnDefinition = "varchar(20) default 'PAYMENT_ON_PICKUP'")
    @Enumerated(EnumType.STRING)
    @NotNull
    private PaymentMethod paymentMethod;

    @Column(name = "pickup_type", nullable = false, columnDefinition = "varchar(20) default 'PICKUP_AT_STORE'")
    @Enumerated(EnumType.STRING)
    @NotNull
    private PickupType pickupType;

    @Column(name = "pickup_fee", nullable = false, columnDefinition = "double default 0")
    @Min(0)
    @NotNull
    private Double pickupFee;

    @Column(name = "pickup_address", nullable = false)
    @NotBlank
    private String pickupAddress;

    @Column(name="full_name", nullable = false, length = 50)
    @Size(max = 50)
    @NotBlank
    private String fullName;

    @Column(name="phone_number", nullable = false, length = 15)
    @Size(max = 15)
    @NotBlank
    private String phoneNumber;

    public BookingDetail(Feedback feedback, Double basePrice, Double extraFee, PaymentMethod paymentMethod, PickupType pickupType, Double pickupFee, String pickupAddress, String fullName, String phoneNumber) {
        this.feedback = feedback;
        this.basePrice = basePrice;
        this.extraFee = extraFee;
        this.paymentMethod = paymentMethod;
        this.pickupType = pickupType;
        this.pickupFee = pickupFee;
        this.pickupAddress = pickupAddress;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
}
