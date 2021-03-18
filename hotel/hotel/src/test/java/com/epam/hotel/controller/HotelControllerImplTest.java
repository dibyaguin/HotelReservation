package com.epam.hotel.controller;

import com.epam.hotel.model.Hotel;
import com.epam.hotel.model.Room;
import com.epam.hotel.service.HotelService;
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
class HotelControllerImplTest {

    @InjectMocks
    private HotelControllerImpl hotelControllerImpl;

    @Mock
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHotels() {
        Hotel mockHotel = new Hotel();
        List<Hotel> mockHotels = new ArrayList<>();
        mockHotels.add(mockHotel);

        Mockito.when(hotelService.getHotels()).thenReturn(mockHotels);
        assertEquals(HttpStatus.OK.value(), hotelControllerImpl.getHotels().getStatusCodeValue());
    }

    @Test
    void testGetHotel() {
        Mockito.when(hotelService.getHotel(any(Integer.class))).thenReturn(new Hotel());
        assertEquals(HttpStatus.OK.value(), hotelControllerImpl.getHotel(1).getStatusCodeValue());
    }

    @Test
    void testCreateHotel() {
        Mockito.when(hotelService.createHotel(any(Hotel.class))).thenReturn(new Hotel());
        assertEquals(HttpStatus.CREATED.value(), hotelControllerImpl.createHotel(new Hotel()).getStatusCodeValue());
    }

    @Test
    void testUpdateHotel() {
        Mockito.when(hotelService.updateHotel(any(Hotel.class), any(Integer.class))).thenReturn(new Hotel());
        assertEquals(HttpStatus.OK.value(), hotelControllerImpl.updateHotel(new Hotel(), 1).getStatusCodeValue());
    }

    @Test
    void testDeleteHotel() {
        Mockito.when(hotelService.deleteHotel(any(Integer.class))).thenReturn(true);
        assertEquals(HttpStatus.OK.value(), hotelControllerImpl.deleteHotel(1).getStatusCodeValue());
    }


    @Test
    void testDeleteHotelFailure() {
        Mockito.when(hotelService.deleteHotel(any(Integer.class))).thenReturn(false);
        assertEquals(HttpStatus.NOT_FOUND.value(), hotelControllerImpl.deleteHotel(1).getStatusCodeValue());
    }

    @Test
    void getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());

        Mockito.when(hotelService.getAvailableRooms(anyString(), anyString()))
                .thenReturn(rooms);
        assertEquals(HttpStatus.OK.value(),
                hotelControllerImpl.getAvailableRooms("2021-04-12", "2021-04-15").getStatusCodeValue());
    }

}