package com.epam.guest.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * The model class for GuestAddress
 */
@Component
@Data
public class GuestAddress {
    private Integer id;
    private String street;
    private String city;
    private Integer zipCode;
    private String state;
    private String country;

}
