package com.epam.guest.controller;

import com.epam.guest.exception.BookingNotAvailableException;
import com.epam.guest.exception.ErrorDetails;
import com.epam.guest.exception.GuestNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GuestControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String GUEST_NOT_FOUND_EXCEPTION_CODE = "Guest-EX-404";
    private static final String GUEST_NOT_FOUND_MSG = "Guest not found";

    private static final String BOOKING_NOT_AVAILABLE_EXCEPTION_CODE = "Booking-Ex-404";
    private static final String BOOKING_NOT_AVAILABLE_MSG = "Room is not available";

    @ExceptionHandler(GuestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleGuestNotFoundException(GuestNotFoundException exception) {
        log.error(GUEST_NOT_FOUND_MSG, exception);
        return buildErrorDetails(GUEST_NOT_FOUND_EXCEPTION_CODE, exception);

    }

    @ExceptionHandler(BookingNotAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleBookingNotAvailableException(BookingNotAvailableException exception) {
        log.error(BOOKING_NOT_AVAILABLE_MSG, exception);
        return buildErrorDetails(BOOKING_NOT_AVAILABLE_EXCEPTION_CODE, exception);

    }

    private ErrorDetails buildErrorDetails(String code, Exception e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(code);
        errorDetails.setMsg(e.getMessage());
        return errorDetails;
    }

}
