package com.example.onboard.constant;

public enum UserStatus {

    INACTIVE(0),
    ACTIVE(1);

    int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
