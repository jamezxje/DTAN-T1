package com.capstone.app.entity.type;

import lombok.Getter;

@Getter
public enum MemberRole {
    ROLE_CUSTOMER("Customer"),
    ROLE_OWNER("Owner"),
    ROLE_ADMIN("Admin");

    private final String value;
    MemberRole(String value) {
        this.value = value;
    }

    public String getTextClass() {
        return switch (this) {
            case ROLE_CUSTOMER -> "primary";
            case ROLE_OWNER -> "info";
            case ROLE_ADMIN -> "danger";
        };
    }
}
