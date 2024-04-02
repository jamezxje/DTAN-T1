package com.capstone.app.entity.type;

import lombok.Getter;

@Getter
public enum Status {
    PENDING ("Pending"),
    APPROVED ("Approved"),
    DONE("Done"),
    REJECTED ("Rejected"),
    CANCELLED ("Cancelled"),
    CANCELLED_AUTOMATIC ("Cancelled Automatic"),
    OUT_OF_TIME ("Out of Time"),
    AVAIABLE ("Avaiable"),
    UNAVAIBLE("Unavaible");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatusClass() {
        return switch (this) {
            case PENDING -> "warning";
            case APPROVED -> "success";
            case REJECTED, OUT_OF_TIME, UNAVAIBLE -> "danger";
            case CANCELLED, CANCELLED_AUTOMATIC -> "secondary";
            case DONE,AVAIABLE -> "primary";


        };
    }

    public boolean isPending() {
        return this == PENDING;
    }

    public boolean isDisabled() {
        return this == CANCELLED || this == CANCELLED_AUTOMATIC || this == OUT_OF_TIME || this == DONE || this == REJECTED;
    }
}
