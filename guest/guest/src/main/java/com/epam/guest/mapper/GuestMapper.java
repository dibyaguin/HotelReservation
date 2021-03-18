package com.epam.guest.mapper;

import com.epam.guest.entity.GuestEntity;
import com.epam.guest.model.Guest;
import com.epam.guest.model.GuestAddress;
import com.epam.guest.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * The Guest Mapper for mapping Enity to Model objects and vice versa
 */
@Component
public class GuestMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private GuestRepository guestRepository;

    /**
     * This method converts a GuestEntity object to a Guest model object
     *
     * @param guestEntity - the Guest Entity object to be converted to a model object
     * @return
     */
    public Guest convertEntityToModel(GuestEntity guestEntity) {
        Guest guest = new Guest();
        guest.setId(guestEntity.getId());
        guest.setName(guestEntity.getName());
        guest.setPhone(guestEntity.getPhone());
        guest.setEmail(guestEntity.getEmail());

        GuestAddress guestAddress = addressMapper.convertEntityToModel(guestEntity.getAddressEntity());
        if (Objects.nonNull(guestAddress)) {
            guest.setGuestAddress(guestAddress);
        }

        return guest;
    }

    /**
     * This method converts a model Guest object to GuestEntity Object
     *
     * @param guest       - the model object to be converted
     * @param guestEntity - the Entity object
     * @return
     */
    public GuestEntity convertModelToEntity(Guest guest, GuestEntity guestEntity) {

        if (Objects.nonNull(guest)) {

            String guestName = guest.getName();
            if (Objects.nonNull(guestName)) {
                guestEntity.setName(guestName);
            }

            String email = guest.getEmail();
            if (Objects.nonNull(email)) {
                guestEntity.setEmail(email);
            }

            Long phone = guest.getPhone();
            if (Objects.nonNull(phone)) {
                guestEntity.setPhone(phone);
            }

            GuestEntity savedGuestEntity = guestRepository.save(guestEntity);

            guestEntity.setAddressEntity(
                    addressMapper.convertModelToEntity(guest.getGuestAddress(), savedGuestEntity));

        }
        return guestEntity;

    }
}

