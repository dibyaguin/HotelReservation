package com.epam.reservation.service;

import com.epam.reservation.model.Reservation;

import java.util.Date;
import java.util.List;

/**
 * The Reservation Service
 */
public interface ReservationService {

    /**
     * This method fetches all the reserved rooms details for specific dates
     *
     * @param fromDate - the booking start date
     * @param toDate   - the booking end date
     * @return
     */
    public List<Reservation> getReservedRooms(Date fromDate, Date toDate);

    /**
     * This method saves a new reservation in the db
     *
     * @param reservation - the model object
     * @return
     */
    public Reservation saveReservation(Reservation reservation);

    /**
     * This method checks whether the reservation is available just prior to booking the reservation
     *
     * @param roomId   - the id of the room
     * @param fromDate - booking start date
     * @param toDate   - booking end date
     * @return
     */
    public Boolean getAvailableReservation(Integer roomId, Date fromDate, Date toDate);
}
