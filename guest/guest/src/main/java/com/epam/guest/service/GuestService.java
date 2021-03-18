package com.epam.guest.service;

import com.epam.guest.model.Guest;
import com.epam.guest.model.Hotel;
import com.epam.guest.model.Reservation;
import com.epam.guest.model.Room;

import java.util.List;

/**
 *  This is the Guest Service Interface for crud operations
 */
public interface GuestService {

    /**
     * This method saves a new guest in the db
     *
     * @param guest - the model guest object
     * @return
     */
    public Guest registerGuest(Guest guest);

    /**
     * This method retrieves a guest details from DB based on the guest id
     *
     * @param guestId - the id of the guest
     * @return
     */
    public Guest getGuest(Integer guestId);

    /**
     * This method updates an existing guest record
     *
     * @param guest - the model guest object to be updated
     * @param guestId - the id of the guest whose details is to be updated
     * @return
     */
    public Guest updateGuest(Guest guest, Integer guestId);

    /**
     * This method removes an guest
     *
     * @param guestId - the id of the guest to be removed
     * @return
     */
    public boolean deleteGuest(Integer guestId);

    /**
     * This method fetches all hotel details
     *
     * @return
     */
    public List<Hotel> viewHotels();

    /**
     * This method fetches all available rooms based on dates
     *
     * @param startDate - start date of booking
     * @param EndDate - end date of booking
     * @return
     */
    public  List<Room> viewAvailableRooms(String startDate, String EndDate);

    /**
     * This method books a reservation
     *
     * @param reservation - the Reservation Model object
     * @return
     */
    public Reservation bookReservation(Reservation reservation);
}
