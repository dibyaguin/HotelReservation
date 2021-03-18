package com.epam.guest.service.helper;

import com.epam.guest.model.Hotel;
import com.epam.guest.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * The feign client interface to communicate with hotel service
 */
@FeignClient(name = "${hotel.service.name}")
public interface HotelFeignClient {

    /**
     * This method fetches all hotels details from Hotel Service
     *
     * @return
     */
    @GetMapping(value = "${hotel.service.getHotels.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Hotel> viewHotels();

    /**
     * This method fetches the available room details for a specified range of date from Hotel Service
     *
     * @param fromDate - the start date of booking
     * @param toDate   - the end date of booking
     * @return
     */
    @GetMapping(value = "${hotel.service.getAvailableRooms.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Room> getAvailableRooms(@RequestParam("fromDate") String fromDate,
                                 @RequestParam("toDate") String toDate);
}
