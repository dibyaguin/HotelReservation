package com.epam.guest.service.impl;

import com.epam.guest.entity.GuestEntity;
import com.epam.guest.exception.BookingNotAvailableException;
import com.epam.guest.exception.GuestNotFoundException;
import com.epam.guest.mapper.GuestMapper;
import com.epam.guest.model.Guest;
import com.epam.guest.model.Hotel;
import com.epam.guest.model.Reservation;
import com.epam.guest.model.Room;
import com.epam.guest.repository.GuestRepository;
import com.epam.guest.service.GuestService;
import com.epam.guest.service.helper.HotelFeignClient;
import com.epam.guest.service.helper.ReservationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The implementation class of GuestService
 */
@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private HotelFeignClient hotelFeignClient;

    @Autowired
    private ReservationFeignClient reservationFeignClient;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    private static final String CIRCUIT_BREAKER_ID = "circuitbreaker";

    /**
     * @see {@link GuestService#registerGuest(Guest)}
     */
    @Override
    public Guest registerGuest(Guest guest) {
        GuestEntity savedGuestEntity = guestRepository.save(guestMapper.convertModelToEntity(guest, new GuestEntity()));
        return guestMapper.convertEntityToModel(savedGuestEntity);
    }

    /**
     * @see {@link GuestService#getGuest(Integer)}
     */
    @Override
    public Guest getGuest(Integer guestId) {
        Guest guest = null;
        Optional<GuestEntity> optionalGuestEntity = guestRepository.findById(guestId);
        if (optionalGuestEntity.isPresent()) {
            guest = guestMapper.convertEntityToModel(optionalGuestEntity.get());
        } else {
            throw new GuestNotFoundException("Guest Id: " + guestId + " not found");
        }
        return guest;
    }

    /**
     * @see {@link GuestService#updateGuest(Guest, Integer)}
     */
    @Override
    public Guest updateGuest(Guest guest, Integer guestId) {
        Guest updatedGuest = null;
        if (Objects.nonNull(guestId)) {
            Optional<GuestEntity> optionalGuestEntity = guestRepository.findById(guestId);
            if (optionalGuestEntity.isPresent()) {
                GuestEntity guestEntity = optionalGuestEntity.get();
                GuestEntity updatedGuestEntity = guestRepository.save(guestMapper.convertModelToEntity(guest, guestEntity));
                updatedGuest = guestMapper.convertEntityToModel(updatedGuestEntity);
            } else {
                throw new GuestNotFoundException("Guest Id: " + guestId + " not found");
            }
        }

        return updatedGuest;
    }

    /**
     * @see {@link GuestService#deleteGuest(Integer)}
     */
    @Override
    public boolean deleteGuest(Integer guestId) {
        boolean isDeleted = false;
        if (Objects.nonNull(guestId) && guestRepository.existsById(guestId)) {
            guestRepository.deleteById(guestId);
            isDeleted = true;
        }
        return isDeleted;
    }

    /**
     * @see {@link GuestService#viewHotels()}
     */
    @Override
    public List<Hotel> viewHotels() {
        return hotelFeignClient.viewHotels();
    }

    /**
     * @see {@link GuestService#viewAvailableRooms(String, String)}
     */
    @Override
    public List<Room> viewAvailableRooms(String startDate, String endDate) {
        return hotelFeignClient.getAvailableRooms(startDate, endDate);
    }

    /**
     * @see {@link GuestService#bookReservation(Reservation)}
     */
    @Override
    public Reservation bookReservation(Reservation reservation) {
        if (reservationFeignClient.getAvailableReservation(reservation.getRoomId(),
                reservation.getReservationFromDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().toString(),
                reservation.getReservationToDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().toString()).booleanValue()) {

            CircuitBreaker circuitBreaker = circuitBreakerFactory.create(CIRCUIT_BREAKER_ID);
            return circuitBreaker.run(() -> reservationFeignClient.bookReservation(reservation),
                    throwable -> fallbackBookReservation());

        } else {
            throw new BookingNotAvailableException("Room id: " + reservation.getRoomId() +
                    " not available.");
        }
    }

    /**
     * This is a fallback method for bookReservation()
     *
     * @return
     */
    private Reservation fallbackBookReservation() {
        return new Reservation();
    }

}
