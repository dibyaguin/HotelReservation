package com.epam.hotel.service;

import com.epam.hotel.model.Hotel;
import com.epam.hotel.model.Room;

import java.util.List;

/**
 * The Hotel Service Interface
 */
public interface HotelService {

    /**
     * This Method retrieves all the hotels
     *
     * @return
     */
    public List<Hotel> getHotels();

    /**
     * This method retrieves a particular hotel based on the hotelId provided
     *
     * @param hotelId - id of the hotel
     * @return
     */
    public Hotel getHotel(Integer hotelId);

    /**
     * This method creates a record for Hotel in the db
     *
     * @param hotel - the Model object for hotel to be inserted in the db
     * @return
     */
    public Hotel createHotel(Hotel hotel);

    /**
     * This method updates an existing record based on the hotel id provided
     *
     * @param hotel   - the model object to be updated
     * @param hotelId - the hotelId which needs to be updated
     * @return
     */
    public Hotel updateHotel(Hotel hotel, Integer hotelId);

    /**
     * This method deletes a hotel
     *
     * @param hotelId - id of the hotel
     * @return
     */
    public boolean deleteHotel(Integer hotelId);

    /**
     * This method fetches all the available rooms for given dates
     *
     * @param fromDate - starting date
     * @param toDate   - ending date
     * @return
     */
    public List<Room> getAvailableRooms(String fromDate, String toDate);
}
