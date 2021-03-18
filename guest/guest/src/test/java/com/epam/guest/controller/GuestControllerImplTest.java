package com.epam.guest.controller;

import com.epam.guest.model.Guest;
import com.epam.guest.model.Hotel;
import com.epam.guest.model.Reservation;
import com.epam.guest.model.Room;
import com.epam.guest.service.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class GuestControllerImplTest {

    @InjectMocks
    private GuestControllerImpl guestControllerImpl;

    @Mock
    private GuestService mockGuestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetGuest() {
        Mockito.when(mockGuestService.getGuest(any(Integer.class))).thenReturn(new Guest());
        assertEquals(HttpStatus.OK.value(), guestControllerImpl.getGuest(1).getStatusCodeValue());
    }

    @Test
    void testRegisterGuest() {
        Mockito.when(mockGuestService.registerGuest(any(Guest.class))).thenReturn(new Guest());
        assertEquals(HttpStatus.CREATED.value(), guestControllerImpl.registerGuest(new Guest()).getStatusCodeValue());
    }

    @Test
    void testUpdateGuest() {
        Mockito.when(mockGuestService.updateGuest(any(Guest.class), any(Integer.class)))
                .thenReturn(new Guest());
        assertEquals(HttpStatus.OK.value(),
                guestControllerImpl.updateGuest(new Guest(), 1).getStatusCodeValue());
    }

    @Test
    void testDeleteGuest() {
        Mockito.when(mockGuestService.deleteGuest(any(Integer.class))).thenReturn(true);
        assertEquals(HttpStatus.OK.value(),
                guestControllerImpl.deleteGuest(1).getStatusCodeValue());
    }

    @Test
    void testDeleteGuestFailure() {
        Mockito.when(mockGuestService.deleteGuest(any(Integer.class))).thenReturn(false);
        assertEquals(HttpStatus.NOT_FOUND.value(),
                guestControllerImpl.deleteGuest(1).getStatusCodeValue());
    }

    @Test
    void testViewHotels() {
        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel = new Hotel();
        hotels.add(hotel);

        Mockito.when(mockGuestService.viewHotels()).thenReturn(hotels);
        assertEquals(HttpStatus.OK.value(),
                guestControllerImpl.viewHotels().getStatusCodeValue());
    }

    @Test
    void testViewAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        rooms.add(room);

        Mockito.when(mockGuestService.viewAvailableRooms(anyString(), anyString()))
                .thenReturn(rooms);
        assertEquals(HttpStatus.OK.value(),
                guestControllerImpl.viewAvailableRooms("2021-04-12", "2021-04-15")
                        .getStatusCodeValue());
    }

    @Test
    void testBookReservation() {
        Mockito.when(mockGuestService.bookReservation(any(Reservation.class)))
                .thenReturn(new Reservation());
        assertEquals(HttpStatus.OK.value(),
                guestControllerImpl.bookReservation(new Reservation())
                        .getStatusCodeValue());
    }

}