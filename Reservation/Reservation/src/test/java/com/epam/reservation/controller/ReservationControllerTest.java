package com.epam.reservation.controller;

import com.epam.reservation.model.Reservation;
import com.epam.reservation.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
class ReservationControllerTest {

    @InjectMocks
    private ReservationController reservationController;

    @Mock
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getReservedRoomsTest() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());

        Mockito.when(reservationService.getReservedRooms(any(Date.class), any(Date.class)))
                .thenReturn(reservations);
        assertEquals(HttpStatus.OK.value(),
                reservationController.getReservedRooms(new Date(), new Date()).getStatusCodeValue());
    }

    @Test
    void getAvailableReservationTest() {
        Mockito.when(reservationService.getAvailableReservation(anyInt(),
                any(Date.class), any(Date.class))).thenReturn(true);
        assertEquals(HttpStatus.OK.value(),
                reservationController.getAvailableReservation(1, new Date(), new Date()).getStatusCodeValue());
    }

    @Test
    void saveReservationTest() {
        Mockito.when(reservationService.saveReservation(any(Reservation.class)))
                .thenReturn(new Reservation());
        assertEquals(HttpStatus.OK.value(),
                reservationController.saveReservation(new Reservation()).getStatusCodeValue());
    }
}