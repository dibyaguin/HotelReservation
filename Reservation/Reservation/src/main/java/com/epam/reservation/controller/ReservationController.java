package com.epam.reservation.controller;

import com.epam.reservation.model.Reservation;
import com.epam.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * The Rest Controller class
 */

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * This method fetches all the reserved rooms for specified Dates
     *
     * @param reservationFrom - reservation From Date
     * @param reservationTo   - reservation to date
     * @return
     */
    @GetMapping("/reservation/getReservedRooms")
    public ResponseEntity<List<Reservation>> getReservedRooms(@RequestParam("fromDate")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationFrom,
                                                              @RequestParam("toDate")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationTo) {
        return new ResponseEntity<>(reservationService.getReservedRooms(reservationFrom, reservationTo),
                HttpStatus.OK);
    }

    /**
     * This method fetches the available reservation prior to confirm any reservation
     *
     * @param roomId          - the id of the room
     * @param reservationFrom - booking from date
     * @param reservationTo   - booking to date
     * @return
     */
    @GetMapping("/reservation/getAvailableReservation")
    public ResponseEntity<Boolean> getAvailableReservation(@RequestParam("roomId") Integer roomId,
                                                           @RequestParam("fromDate")
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationFrom,
                                                           @RequestParam("toDate")
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationTo
    ) {
        return new ResponseEntity<>(reservationService.getAvailableReservation(roomId, reservationFrom, reservationTo),
                HttpStatus.OK);
    }

    /**
     * This method saves a new reservation
     *
     * @param reservation - the model object
     * @return
     */
    @PostMapping("/reservation/bookReservation")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.OK);
    }


}
