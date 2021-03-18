package com.epam.hotel.constant;

public enum ActiveType {
    YES("Y"),
    NO("N");

    private String value;

    private ActiveType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
