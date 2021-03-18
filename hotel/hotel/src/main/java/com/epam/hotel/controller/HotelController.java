package com.epam.hotel.controller;

import com.epam.hotel.model.Hotel;
import com.epam.hotel.model.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * This is the Hotel Controller Interface
 */

public interface HotelController {

    /**
     * This method fetches all the available hotels details
     *
     * @return
     */
    public ResponseEntity<List<Hotel>> getHotels();

    /**
     * This method fetches a hotel detail based on the given hotel id
     *
     * @param hotelId - the id of the hotel
     * @return
     */
    public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") Integer hotelId);

    /**
     * This method saves a new Hotel details in the DB
     *
     * @param hotel - the Model Hotel Object to be saved in the DB
     * @return
     */
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel);

    /**
     * This method updates an existing hotel details based on the given hotel id
     *
     * @param hotel   - the model object of Hotel to be updated
     * @param hotelId - the hotel id whose details is to be updated
     * @return
     */
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable Integer hotelId);

    /**
     * This method deletes an existing hotel from the DB based on the given DB
     *
     * @param hotelId - the id of the hotel to be deleted
     * @return
     */
    public ResponseEntity<Integer> deleteHotel(@PathVariable Integer hotelId);

    /**
     * This method fetches all the available rooms based on the dates provided
     *
     * @param fromDate - the starting date from which the hotel is being searched for booking
     * @param toDate   - the starting end for which the hotel is being searched for booking
     * @return
     */
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestParam("fromDate") String fromDate,
                                                        @RequestParam("toDate") String toDate);

}
