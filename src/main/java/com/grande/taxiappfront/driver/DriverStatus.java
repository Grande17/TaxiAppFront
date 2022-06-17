package com.grande.taxiappfront.driver;

import lombok.Getter;

@Getter
public enum DriverStatus {
    ACTIVE("ACTIVE"),
    BUSY("BUSY"),
    BREAK("BREAK"),
    INACTIVE("INACTIVE"),
    ACCOUNT_DELETED("ACCOUNT_DELETED");

    public String name;

    DriverStatus(String name) {
        this.name=name;

    }

    @Override
    public String toString() {
        return name;
    }
}
