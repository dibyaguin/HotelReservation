package com.epam.guest.mapper;

import com.epam.guest.entity.AddressEntity;
import com.epam.guest.entity.GuestEntity;
import com.epam.guest.model.GuestAddress;
import com.epam.guest.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * This class is a mapper to convert Address model objects to Entity objects and vice versa
 */
@Component
public class AddressMapper {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * This method converts a AddressEntity object to a Address Model Object
     *
     * @param addressEntity - the entity object to be converted to Model object
     * @return
     */
    public GuestAddress convertEntityToModel(AddressEntity addressEntity) {

        GuestAddress guestAddress = null;

        if (Objects.nonNull(addressEntity)) {
            guestAddress = new GuestAddress();
            guestAddress.setId(addressEntity.getId());
            guestAddress.setStreet(addressEntity.getStreet());
            guestAddress.setCity(addressEntity.getCity());
            guestAddress.setZipCode(addressEntity.getZipcode());
            guestAddress.setState(addressEntity.getState());
            guestAddress.setCountry(addressEntity.getCountry());
        }

        return guestAddress;
    }

    /**
     * This method converts Model object to Entity Object
     *
     * @param guestAddress - the model object
     * @param guestEntity  - the entity object
     * @return
     */
    public AddressEntity convertModelToEntity(GuestAddress guestAddress, GuestEntity guestEntity) {

        AddressEntity addressEntity = null;

        if (Objects.nonNull(guestAddress)) {

            addressEntity = getAddressEntity(guestAddress, guestEntity);

            String street = guestAddress.getStreet();
            if (Objects.nonNull(street)) {
                addressEntity.setStreet(street);
            }

            String city = guestAddress.getCity();
            if (Objects.nonNull(city)) {
                addressEntity.setCity(city);
            }

            Integer zipcode = guestAddress.getZipCode();
            if (Objects.nonNull(zipcode)) {
                addressEntity.setZipcode(zipcode);
            }

            String state = guestAddress.getState();
            if (Objects.nonNull(state)) {
                addressEntity.setState(state);
            }

            String country = guestAddress.getCountry();
            if (Objects.nonNull(country)) {
                addressEntity.setCountry(guestAddress.getCountry());

            }

        }
        return addressEntity;
    }

    /**
     * This method checks for any existing AddressEntity and
     *
     * @param guestAddress
     * @param guestEntity
     * @return
     */
    private AddressEntity getAddressEntity(GuestAddress guestAddress, GuestEntity guestEntity) {
        AddressEntity addressEntity = new AddressEntity();

        if (Objects.nonNull(guestAddress.getId()) && guestAddress.getId() != 0) {
            Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(guestAddress.getId());
            if (optionalAddressEntity.isPresent()) {
                addressEntity = optionalAddressEntity.get();
            }
        } else {
            addressEntity.setGuestEntity(guestEntity);
        }
        return addressEntity;
    }

}
