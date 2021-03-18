package com.epam.guest.service.helper;

import com.epam.guest.model.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The feign client to communicate with Reservation Service
 */
@FeignClient(name = "${reservation.service.name}")
public interface ReservationFeignClient {

    /**
     * This method fetches the available Reservation based on roomId, and dates
     *
     * @param roomId   - the id of the room
     * @param fromDate - booking start date
     * @param toDate   - booking end date
     * @return
     */
    @GetMapping(value = "${reservation.service.getAvailableReservation.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean getAvailableReservation(@RequestParam Integer roomId,
                                    @RequestParam("fromDate") String fromDate,
                                    @RequestParam("toDate") String toDate);

    /**
     * This method saves a new reservation the DB
     *
     * @param reservation - the model Reservation object
     * @return
     */
    @PostMapping(value = "${reservation.service.bookReservation.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Reservation bookReservation(@RequestBody Reservation reservation);
}
