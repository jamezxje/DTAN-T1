package com.capstone.app.entity.type;

import lombok.Getter;

@Getter
public enum TransactionMode {
    DEPOSIT("+"), WITHDRAW("-");
    private final String symbol;

    TransactionMode(String symbol) {
        this.symbol = symbol;
    }

    public String getTextClass(){
        return switch (this) {
            case DEPOSIT -> "success";
            case WITHDRAW -> "danger";
        };
    }
}
