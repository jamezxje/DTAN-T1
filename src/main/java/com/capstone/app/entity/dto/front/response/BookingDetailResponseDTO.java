package com.capstone.app.entity.dto.front.response;

import com.capstone.app.entity.type.PaymentMethod;
import com.capstone.app.entity.type.PickupType;
import com.capstone.app.entity.type.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDetailResponseDTO {

	private Integer bookingId;
	private String imageUrl;
	private String carModel;
	private Double deposit;
	private Double basePrice;
	private LocalDateTime pickupTime;
	private LocalDateTime returnTime;
	private LocalDateTime actualReturnTime;
	private String address;
	private String fullName;
	private String phoneNumber;
	private PickupType pickupType;
	private PaymentMethod paymentMethod;
	private Status status;
	private Double total;

	private long days;
	private Double subTotal;

	private Integer feedbackId;
	private String feedbackContent;
	private Integer feedbackPoint;
	private LocalDateTime feedbackDate;
	private LocalDateTime feedbackUpdatedAt;

	public BookingDetailResponseDTO(Integer bookingId, String imageUrl, String carModel,
									Double deposit, Double basePrice, LocalDateTime pickupTime,
									LocalDateTime returnTime, LocalDateTime actualReturnTime,
									String address, String fullName, String phoneNumber,
									PickupType pickupType, PaymentMethod paymentMethod, Status status, Double total,
									Integer feedbackId, String feedbackContent, Integer feedbackPoint,
									LocalDateTime feedbackDate, LocalDateTime feedbackUpdatedAt) {
		this.bookingId = bookingId;
		this.imageUrl = imageUrl;
		this.carModel = carModel;
		this.deposit = deposit;
		this.basePrice = basePrice;
		this.pickupTime = pickupTime;
		this.returnTime = returnTime;
		this.actualReturnTime = actualReturnTime;
		this.address = address;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.pickupType = pickupType;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.total = total;
		this.feedbackId = feedbackId;
		this.feedbackContent = feedbackContent;
		this.feedbackPoint = feedbackPoint;
		this.feedbackDate = feedbackDate;
		this.feedbackUpdatedAt = feedbackUpdatedAt;
	}


}


