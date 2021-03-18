package com.epam.guest.controller;

import com.epam.guest.model.Guest;
import com.epam.guest.model.Hotel;
import com.epam.guest.model.Reservation;
import com.epam.guest.model.Room;
import com.epam.guest.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The implementation class of Guest Controller
 */
@RestController
public class GuestControllerImpl implements GuestController {

    @Autowired
    private GuestService guestService;

    /**
     * @see {@link GuestController#getGuest(Integer)}  }
     */
    @Override
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<Guest> getGuest(@PathVariable("guestId") Integer guestId) {
        return new ResponseEntity<>(guestService.getGuest(guestId), HttpStatus.OK);
    }

    /**
     * @see {@link GuestController#registerGuest(Guest)}  }
     */
    @Override
    @PostMapping("/guest")
    public ResponseEntity<Guest> registerGuest(@RequestBody Guest guest) {
        return new ResponseEntity<>(guestService.registerGuest(guest), HttpStatus.CREATED);
    }

    /**
     * @see {@link GuestController#updateGuest(Guest, Integer)}  }
     */
    @Override
    @PutMapping("/guest/{guestId}")
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest, @PathVariable Integer guestId) {
        return new ResponseEntity<>(guestService.updateGuest(guest, guestId), HttpStatus.OK);
    }
    /**
     * @see {@link GuestController#deleteGuest(Integer)}  }
     */
    @Override
    @DeleteMapping("/guest/{guestId}")
    public ResponseEntity<Integer> deleteGuest(@PathVariable Integer guestId) {

        ResponseEntity<Integer> responseEntity = null;
        if (guestService.deleteGuest(guestId)) {
            responseEntity = new ResponseEntity<>(guestId, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(guestId, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    /**
     * @see {@link GuestController#viewHotels()}  }
     */
    @Override
    @GetMapping("/guest/viewHotels")
    public ResponseEntity<List<Hotel>> viewHotels() {
        return new ResponseEntity<>(guestService.viewHotels(), HttpStatus.OK);
    }

    /**
     * @see {@link GuestController#viewAvailableRooms(String, String)}  }
     */
    @Override
    @GetMapping("/guest/availableRooms")
    public ResponseEntity<List<Room>> viewAvailableRooms(@RequestParam("fromDate") String startDate,
                                                         @RequestParam("toDate") String endDate) {
        return new ResponseEntity<>(guestService.viewAvailableRooms(startDate, endDate),
                HttpStatus.OK);
    }

    /**
     * @see {@link GuestController#bookReservation(Reservation)}  }
     */
    @Override
    @PostMapping("/guest/bookReservation")
    public ResponseEntity<Reservation> bookReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(guestService.bookReservation(reservation), HttpStatus.OK);
    }

}
