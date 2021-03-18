package com.epam.guest.service.impl;

import com.epam.guest.entity.GuestEntity;
import com.epam.guest.exception.GuestNotFoundException;
import com.epam.guest.mapper.GuestMapper;
import com.epam.guest.model.Guest;
import com.epam.guest.model.Hotel;
import com.epam.guest.model.Reservation;
import com.epam.guest.model.Room;
import com.epam.guest.repository.GuestRepository;
import com.epam.guest.service.helper.HotelFeignClient;
import com.epam.guest.service.helper.ReservationFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class GuestServiceImplTest {

    @InjectMocks
    private GuestServiceImpl guestServiceImpl;

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private GuestMapper guestMapper;

    @Mock
    private HotelFeignClient hotelFeignClient;

    @Mock
    private ReservationFeignClient reservationFeignClient;

    @Mock
    private CircuitBreakerFactory circuitBreakerFactory;

    @Mock
    private CircuitBreaker mockCircuitBreaker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerGuestTest() {
        Mockito.when(guestMapper.convertModelToEntity(any(Guest.class), any(GuestEntity.class)))
                .thenReturn(new GuestEntity());
        Mockito.when(guestRepository.save(any(GuestEntity.class))).thenReturn(new GuestEntity());
        Mockito.when(guestMapper.convertEntityToModel(any(GuestEntity.class))).thenReturn(new Guest());
        assertNotNull(guestServiceImpl.registerGuest(new Guest()));
    }

    @Test
    void getGuestTest() {
        Mockito.when(guestRepository.findById(any(Integer.class)))
                .thenReturn(Optional.of(new GuestEntity()));
        Mockito.when(guestMapper.convertEntityToModel(any(GuestEntity.class))).thenReturn(new Guest());
        assertNotNull(guestServiceImpl.getGuest(1));
    }

    @Test
    void getGuestNotFoundExceptionTest() {
        Mockito.when(guestRepository.findById(any(Integer.class)))
                .thenReturn(Optional.ofNullable(null));
        assertThrows(GuestNotFoundException.class, () -> {
            guestServiceImpl.getGuest(1);
        });
    }

    @Test
    void updateGuestTest() {
        Mockito.when(guestRepository.findById(any(Integer.class)))
                .thenReturn(Optional.of(new GuestEntity()));
        Mockito.when(guestMapper.convertModelToEntity(any(Guest.class), any(GuestEntity.class)))
                .thenReturn(new GuestEntity());
        Mockito.when(guestRepository.save(any(GuestEntity.class))).thenReturn(new GuestEntity());
        Mockito.when(guestMapper.convertEntityToModel(any(GuestEntity.class))).thenReturn(new Guest());
        assertNotNull(guestServiceImpl.updateGuest(new Guest(), 1));

    }

    @Test
    void getGuestNotFoundExceptionTestForUpdateGuest() {
        Mockito.when(guestRepository.findById(any(Integer.class)))
                .thenReturn(Optional.ofNullable(null));
        assertThrows(GuestNotFoundException.class, () -> {
            guestServiceImpl.updateGuest(new Guest(), 1);
        });
    }

    @Test
    void deleteGuestTest() {
        Mockito.when(guestRepository.existsById(anyInt())).thenReturn(true);
        Mockito.doNothing().when(guestRepository).deleteById(anyInt());
        assertTrue(guestServiceImpl.deleteGuest(1));

    }

    @Test
    void viewHotelsTest() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel());

//        Mockito.when(circuitBreakerFactory.create(anyString())).thenReturn(new CircuitBreaker() {
//        })
        Mockito.when(hotelFeignClient.viewHotels()).thenReturn(hotels);
        assertNotNull(guestServiceImpl.viewHotels());
    }

    @Test
    void viewAvailableRoomsTest() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());

        Mockito.when(hotelFeignClient.getAvailableRooms(anyString(), anyString()))
                .thenReturn(rooms);
        assertNotNull(guestServiceImpl.viewAvailableRooms("2021-04-12", "2021-04-15"));
    }

//    @Test
//    void bookReservationTest() {
//        Reservation reservation = new Reservation();
//        reservation.setRoomId(1);
//        reservation.setReservationFromDate(new Date());
//        reservation.setReservationToDate(new Date());
//
//        Mockito.when(reservationFeignClient.getAvailableReservation(anyInt(), anyString(), anyString()))
//                .thenReturn(true);
//
//        Mockito.when(circuitBreakerFactory.create(anyString())).thenReturn(mockCircuitBreaker);
//
//        Mockito.when(reservationFeignClient.bookReservation(any(Reservation.class)))
//                .thenReturn(reservation);
//
//        Mockito.when( mockCircuitBreaker.run(any())).thenReturn(new Reservation());
//       // Mockito.when( mockCircuitBreaker.run(any(),any())).thenReturn(new Exception());
//        assertNotNull(guestServiceImpl.bookReservation(reservation));
//        //assertThrows(guestServiceImpl.bookReservation(reservation));
////        assertTrue(true);
//    }
}