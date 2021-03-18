package com.epam.reservation.service.impl;

import com.epam.reservation.constant.BookingStatus;
import com.epam.reservation.entity.ReservationEntity;
import com.epam.reservation.mapper.ReservationMapper;
import com.epam.reservation.model.Reservation;
import com.epam.reservation.repository.ReservationRepository;
import com.epam.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The implementation class of ReservationService
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    /**
     * @see {@link ReservationService#getReservedRooms(Date, Date)}
     */
    @Override
    public List<Reservation> getReservedRooms(Date fromDate, Date toDate) {
        List<ReservationEntity> reservationEntities = reservationRepository.
                findByReservationFromDateAndReservationToDateAndBookingStatus(fromDate,
                        toDate, BookingStatus.BOOKED.getStatus());
        return reservationMapper.convertEntityToModel(reservationEntities);
    }

    /**
     * @see {@link ReservationService#saveReservation(Reservation)}
     */
    @Override
    public Reservation saveReservation(Reservation reservation) {
        ReservationEntity savedReservationEntity = reservationRepository.save(
                reservationMapper.convertModelToEntity(reservation));
        return reservationMapper.convertEntityToModel(savedReservationEntity);
    }

    /**
     * @see {@link ReservationService#getAvailableReservation(Integer, Date, Date)}
     */
    @Override
    public Boolean getAvailableReservation(Integer roomId, Date fromDate, Date toDate) {
        Optional<ReservationEntity> optionalReservationEntity =
                reservationRepository.findByRoomIdAndReservationFromDateAndReservationToDate(
                        roomId, fromDate, toDate);

       return optionalReservationEntity.isEmpty() ? true : false;
    }


}
