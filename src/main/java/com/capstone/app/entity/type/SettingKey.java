package com.capstone.app.entity.type;

public enum SettingKey {
    SYSTEM_NAME("System name"),
    SYSTEM_WORKING_HOURS("Working hours"),
    SYSTEM_EMAIL("System email"),
    SYSTEM_INTRODUCTION("Introduction"),
    SYSTEM_CONTACT("Contact"),
    SYSTEM_ADDRESS("Address"),
    SYSTEM_FACEBOOK("Facebook"),

    SYSTEM_SERVICE_FEE("Service fee"),
    SYSTEM_DEPOSIT("Deposit"),
    SYSTEM_LOGO("Logo"),
    SYSTEM_POLICY("Policy"),
    SYSTEM_TERMS("Terms"),
    SYSTEM_PRIVACY("Privacy");

    private final String key;

    SettingKey(String key) {
        this.key = key;
    }

}
