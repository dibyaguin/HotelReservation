package com.epam.guest.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * The model class for Guest
 */
@Component
@Data
public class Guest {

    private Integer id;
    private String name;
    private String email;
    private Long phone;
    private GuestAddress guestAddress;

}
