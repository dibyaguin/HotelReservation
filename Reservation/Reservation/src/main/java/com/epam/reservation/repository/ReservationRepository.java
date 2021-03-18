package com.epam.reservation.repository;

import com.epam.reservation.entity.ReservationEntity;
import com.epam.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The repository for ReservationEntity
 */
@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    List<ReservationEntity> findByReservationFromDateAndReservationToDateAndBookingStatus(Date fromDate,
                                                                                          Date toDate,
                                                                                          String bookingStatus);

    Optional<ReservationEntity> findByRoomIdAndReservationFromDateAndReservationToDate(Integer RoomId,
                                                                                 Date fromDate,
                                                                                 Date toDate);
}
