package com.capstone.app.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    PAYMENT_ON_PICKUP ("Payment on Pickup"),
    PAYMENT_ONLINE ("Payment using balance wallet");

    private final String paymentMethod;
}
