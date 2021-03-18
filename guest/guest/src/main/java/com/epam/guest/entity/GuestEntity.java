package com.epam.guest.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * The GuestEntity class
 */
@Data
@Entity
@Table(name = "guests")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "guestEntity")
    private AddressEntity addressEntity;
}

