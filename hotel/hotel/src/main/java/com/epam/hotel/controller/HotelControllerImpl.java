package com.epam.hotel.controller;

import com.epam.hotel.model.Hotel;
import com.epam.hotel.model.Room;
import com.epam.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Hotel Controller Implementation
 */
@RestController
public class HotelControllerImpl implements HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * @see {@link HotelController#getHotels()}
     */
    @Override
    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getHotels() {
        return new ResponseEntity<>(hotelService.getHotels(), HttpStatus.OK);
    }

    /**
     * @see {@link HotelController#getHotel(Integer)}
     */
    @Override
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") Integer hotelId) {
        return new ResponseEntity<>(hotelService.getHotel(hotelId), HttpStatus.OK);
    }

    /**
     * @see {@link HotelController#createHotel(Hotel)}
     */
    @Override
    @PostMapping("/hotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    /**
     * @see {@link HotelController#updateHotel(Hotel, Integer)}
     */
    @Override
    @PutMapping("/hotel/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable Integer hotelId) {
        return new ResponseEntity<>(hotelService.updateHotel(hotel, hotelId), HttpStatus.OK);
    }

    /**
     * @see {@link HotelController#deleteHotel(Integer)}
     */
    @Override
    @DeleteMapping("/hotel/{hotelId}")
    public ResponseEntity<Integer> deleteHotel(@PathVariable Integer hotelId) {

        ResponseEntity<Integer> responseEntity = null;
        if (hotelService.deleteHotel(hotelId)) {
            responseEntity = new ResponseEntity<>(hotelId, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(hotelId, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    /**
     * @see {@link HotelController#getAvailableRooms(String, String)}
     */
    @Override
    @GetMapping("/hotel/availableRooms")
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestParam("fromDate") String fromDate,
                                                        @RequestParam("toDate") String toDate) {
        return new ResponseEntity<>(hotelService.getAvailableRooms(fromDate, toDate), HttpStatus.OK);
    }
}
