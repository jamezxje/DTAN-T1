package com.capstone.app.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PickupType {
    PICKUP_AT_STORE ("Pick up the car at our store", 0),
    PICKUP_AT_HOME ("Receive the car at your home", 200);

    private final String pickupType;

    private final Integer pickupPrice;
}
