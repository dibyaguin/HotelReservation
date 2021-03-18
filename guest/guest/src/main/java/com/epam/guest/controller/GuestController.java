package com.epam.guest.controller;

import com.epam.guest.model.Guest;
import com.epam.guest.model.Hotel;
import com.epam.guest.model.Reservation;
import com.epam.guest.model.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * This is the Guest Controller
 */
public interface GuestController {

    /**
     * This method fetches a Guest based on the given guestId
     *
     * @param guestId - id of the guest
     * @return
     */
    public ResponseEntity<Guest> getGuest(Integer guestId);

    /**
     * This method saves a guest in the DB
     *
     * @param guest - the model Guest object to be saved
     * @return
     */
    public ResponseEntity<Guest> registerGuest(Guest guest);

    /**
     * This method updates a guest record based on the guest id provided
     *
     * @param guest   - the model Guest object to be updated
     * @param guestId - id of the guest whose record is to be updated
     * @return
     */
    public ResponseEntity<Guest> updateGuest(Guest guest, Integer guestId);

    /**
     * This method deletes a guest based on the guest id
     *
     * @param guestId - the id of the guest to be deleted
     * @return
     */
    public ResponseEntity<Integer> deleteGuest(Integer guestId);

    /**
     * This method fetches all hotel details
     *
     * @return
     */
    public ResponseEntity<List<Hotel>> viewHotels();

    /**
     * This method fetches all available rooms for specific dates
     *
     * @param startDate - the start date of hotel booking
     * @param endDate   - the end date of hotel booking
     * @return
     */
    public ResponseEntity<List<Room>> viewAvailableRooms(String startDate, String endDate);

    /**
     * This method books an reservation
     *
     * @param reservation - the Reservation Model object
     * @return
     */
    public ResponseEntity<Reservation> bookReservation(Reservation reservation);
}
