package com.epam.reservation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * The ReservationEntity class
 */
@Data
@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "guest_id")
    private Integer guestId;

    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "room_id")
    private Integer roomId;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "booking_from_date")
    private Date reservationFromDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "booking_end_date")
    private Date reservationToDate;

    @Column(name = "booking_status")
    private String bookingStatus;

}

