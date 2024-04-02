package com.capstone.app.entity;

import com.capstone.app.entity.type.Status;
import com.capstone.app.util.DateTimeUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BookingDetail bookingDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "car_model", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String carModel;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "booking_request_time", nullable = false)
    @NotNull
    private LocalDateTime bookingRequestTime;

    @Column(name = "booking_pickup_time", nullable = false)
    @NotNull
    private LocalDateTime bookingPickupTime;

    @Column(name = "booking_return_time", nullable = false)
    @NotNull
    private LocalDateTime bookingReturnTime;

    @Column(name = "booking_actual_return_time")
    private LocalDateTime bookingActualReturnTime;

    @Column(name = "status", nullable = false, columnDefinition = "varchar(20) default 'PENDING'")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    public Booking(Car car, Member member, String carModel, Double totalPrice, LocalDateTime bookingRequestTime, LocalDateTime bookingPickupTime, LocalDateTime bookingReturnTime, LocalDateTime bookingActualReturnTime, Status status, Boolean isDeleted) {
        this.car = car;
        this.member = member;
        this.carModel = carModel;
        this.totalPrice = totalPrice;
        this.bookingRequestTime = bookingRequestTime;
        this.bookingPickupTime = bookingPickupTime;
        this.bookingReturnTime = bookingReturnTime;
        this.bookingActualReturnTime = bookingActualReturnTime;
        this.status = status;
        this.isDeleted = isDeleted;
    }


    public Double calculateTotalPrice() {

        long diffDays = DateTimeUtils.calculateTotalDays(bookingPickupTime, bookingReturnTime);

        if (bookingActualReturnTime != null && bookingActualReturnTime.isAfter(bookingReturnTime)) {
            diffDays = DateTimeUtils.calculateTotalDays(bookingPickupTime, bookingReturnTime);
        }

        return (diffDays * bookingDetail.getBasePrice()) + bookingDetail.getPickupType().getPickupPrice();
    }
    public Double calculateFeePrice() {
        double totalDays = DateTimeUtils.calculateTotalDays(bookingPickupTime, bookingReturnTime);
        return totalDays * bookingDetail.getBasePrice() * 0.15;
    }
    public Double calculateExtraFeePrice() {

        if (bookingActualReturnTime == null || bookingActualReturnTime.isBefore(bookingReturnTime)) {
            return 0.0;
        }

        long diffDays = DateTimeUtils.calculateTotalDays(bookingPickupTime, bookingActualReturnTime);
        Double extraFee = (diffDays * bookingDetail.getBasePrice() * 0.15) - calculateFeePrice();

        return extraFee;
    }
}
