package com.epam.reservation.mapper;

import com.epam.reservation.constant.BookingStatus;
import com.epam.reservation.entity.ReservationEntity;
import com.epam.reservation.model.Reservation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The mapper class for ReservationEntity to Reservation Model
 */
@Component
public class ReservationMapper {

    /**
     * This method converts list of Reservation Entities to reservation model objects
     *
     * @param reservationEntities - the list of entity objects to be converted
     * @return
     */
    public List<Reservation> convertEntityToModel(List<ReservationEntity> reservationEntities) {

        return CollectionUtils.emptyIfNull(reservationEntities)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());

    }

    /**
     * This method converts a ReservationEntity object to a Reservation object
     *
     * @param reservationEntity -  the entity object to be converted
     * @return
     */
    public Reservation convertEntityToModel(ReservationEntity reservationEntity) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationEntity.getId());
        reservation.setGuestId(reservationEntity.getGuestId());
        reservation.setHotelId(reservationEntity.getHotelId());
        reservation.setRoomId(reservationEntity.getRoomId());
        reservation.setReservationToDate(reservationEntity.getReservationToDate());
        reservation.setReservationFromDate(reservationEntity.getReservationFromDate());
        reservation.setReservationStatus(reservationEntity.getBookingStatus());
        return reservation;
    }

    /**
     * This method converts a Reservation Model object to Reservation Entity object
     *
     * @param reservation - the model object
     * @return
     */
    public ReservationEntity convertModelToEntity(Reservation reservation) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setHotelId(reservation.getHotelId());
        reservationEntity.setGuestId(reservation.getGuestId());
        reservationEntity.setRoomId(reservation.getRoomId());
        reservationEntity.setReservationFromDate(reservation.getReservationFromDate());
        reservationEntity.setReservationToDate(reservation.getReservationToDate());
        reservationEntity.setBookingStatus(BookingStatus.BOOKED.getStatus());
        return reservationEntity;
    }
}
