package com.capstone.app.entity.dto.front.request;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.BookingDetail;
import com.capstone.app.entity.type.PaymentMethod;
import com.capstone.app.entity.type.PickupType;
import com.capstone.app.entity.type.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequestDTO {
    private Integer bookingId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private LocalDateTime pickupTime;
    private LocalDateTime returnTime;
    private PaymentMethod paymentMethod;
    private PickupType pickupType;
    private Double totalPrice;
    private Double feePrice;
    private Double basePrice;

    public Booking toEntity() {

        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setFullName(this.fullName);
        bookingDetail.setPickupAddress(this.address);
        bookingDetail.setPhoneNumber(this.phoneNumber);
        bookingDetail.setPaymentMethod(this.paymentMethod);
        bookingDetail.setPickupType(this.pickupType);
        bookingDetail.setPickupFee(this.feePrice);
        bookingDetail.setBasePrice(this.basePrice);

        Booking booking = new Booking();
        if (this.bookingId != null) {
            booking.setBookingId(this.bookingId);
        }
        booking.setTotalPrice(this.totalPrice);
        booking.setBookingRequestTime(LocalDateTime.now());
        booking.setBookingPickupTime(this.pickupTime);
        booking.setBookingReturnTime(this.returnTime);
        booking.setIsDeleted(false);
        booking.setStatus(Status.PENDING);
        booking.setBookingDetail(bookingDetail);

        return booking;
    }
}
