package com.epam.guest.model;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The model class for Hotel
 */
@Component
public class Hotel {

    private Integer id;
    private String name;
    private String email;
    private Long phone;
    private GuestAddress guestAddress;
    private List<Room> rooms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public GuestAddress getAddress() {
        return guestAddress;
    }

    public void setAddress(GuestAddress guestAddress) {
        this.guestAddress = guestAddress;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
