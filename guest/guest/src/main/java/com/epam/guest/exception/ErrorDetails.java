package com.epam.guest.exception;

import lombok.Data;

@Data
public class ErrorDetails {

    private String code;
    private String msg;

    public ErrorDetails() {
        super();
    }

    public ErrorDetails(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
