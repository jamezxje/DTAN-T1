package com.capstone.app.entity.type;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    AUTO("Auto");

    private final String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public String getStatusClass() {
        return switch (this) {
            case PENDING -> "warning";
            case APPROVED -> "success";
            case REJECTED-> "danger";
            case AUTO -> "secondary";
        };
    }

    public boolean isPending() {
        return this == PENDING;
    }
}
