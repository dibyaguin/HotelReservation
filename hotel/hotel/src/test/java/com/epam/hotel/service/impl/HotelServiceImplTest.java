package com.epam.hotel.service.impl;

import com.epam.hotel.entity.HotelEntity;
import com.epam.hotel.entity.RoomEntity;
import com.epam.hotel.mapper.HotelMapper;
import com.epam.hotel.mapper.RoomMapper;
import com.epam.hotel.model.Hotel;
import com.epam.hotel.model.Reservation;
import com.epam.hotel.model.Room;
import com.epam.hotel.repository.HotelRepository;
import com.epam.hotel.repository.RoomRepository;
import com.epam.hotel.service.helper.ReservationFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class HotelServiceImplTest {

    @InjectMocks
    private HotelServiceImpl hotelServiceImpl;

    @Mock
    private HotelMapper hotelMapper;

    @Mock
    private RoomMapper roomMapper;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationFeignClient reservationFeignClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetHotels() {
        List<HotelEntity> mockHotelEntities = new ArrayList<>();
        mockHotelEntities.add(new HotelEntity());

        List<Hotel> mockHotels = new ArrayList<>();
        mockHotels.add(new Hotel());

        Mockito.when(hotelRepository.findAll()).thenReturn(mockHotelEntities);
        Mockito.when(hotelMapper.convertEntityToModel(any(List.class))).thenReturn(mockHotels);
        assertNotNull(hotelServiceImpl.getHotels());
    }

    @Test
    void testGetHotel() {
        Mockito.when(hotelRepository.findById(any(Integer.class))).thenReturn(Optional.of(new HotelEntity()));
        Mockito.when(hotelMapper.convertEntityToModel(any(HotelEntity.class))).thenReturn(new Hotel());
        assertNotNull(hotelServiceImpl.getHotel(1));
    }

    @Test
    void testCreateHotel() {
        Mockito.when(hotelMapper.convertModelToEntity(any(Hotel.class), any(HotelEntity.class)))
                .thenReturn(new HotelEntity());
        Mockito.when(hotelRepository.save(any(HotelEntity.class))).thenReturn(new HotelEntity());
        Mockito.when(hotelMapper.convertEntityToModel(any(HotelEntity.class))).thenReturn(new Hotel());
        assertNotNull(hotelServiceImpl.createHotel(new Hotel()));
    }

    @Test
    void testUpdateHotel() {
        Mockito.when(hotelRepository.findById(any(Integer.class)))
                .thenReturn(Optional.of(new HotelEntity()));
        Mockito.when(hotelMapper.convertModelToEntity(any(Hotel.class), any(HotelEntity.class)))
                .thenReturn(new HotelEntity());
        Mockito.when(hotelRepository.save(any(HotelEntity.class))).thenReturn(new HotelEntity());
        Mockito.when(hotelMapper.convertEntityToModel(any(HotelEntity.class))).thenReturn(new Hotel());
        assertNotNull(hotelServiceImpl.updateHotel(new Hotel(), 1));
    }

    @Test
    void testDeleteHotel() {
        Mockito.when(hotelRepository.existsById(anyInt())).thenReturn(true);
        Mockito.doNothing().when(hotelRepository).deleteById(anyInt());
        assertTrue(hotelServiceImpl.deleteHotel(1));
    }

    @Test
    void testgetAvailableRooms() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setRoomId(1);
        reservations.add(reservation);

        List<RoomEntity> roomEntities = new ArrayList<>();
        RoomEntity roomEntity = new RoomEntity();
        roomEntities.add(roomEntity);

        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        rooms.add(room);

        Mockito.when(reservationFeignClient.getReservedRooms(anyString(), anyString()))
                .thenReturn(reservations);
        Mockito.when(roomRepository.findByIdNotIn(anyList())).thenReturn(roomEntities);
        Mockito.when(roomMapper.convertEntityToModel(roomEntities)).thenReturn(rooms);
        assertNotNull(hotelServiceImpl.getAvailableRooms("2021-04-12", "2021-04-16"));


    }
}