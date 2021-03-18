package com.epam.hotel.mapper;

import com.epam.hotel.entity.AddressEntity;
import com.epam.hotel.entity.HotelEntity;
import com.epam.hotel.model.Address;
import com.epam.hotel.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * This the Mapper class for mapping between AddressEntity and Address Model
 */
@Component
public class AddressMapper {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * This method converts AddressEntity object to Address Model object
     *
     * @param addressEntity - the AddressEntity to be converted to model object
     * @return
     */
    public Address convertEntityToModel(AddressEntity addressEntity) {

        Address address = null;

        if (Objects.nonNull(addressEntity)) {
            address = new Address();
            address.setId(addressEntity.getId());
            address.setStreet(addressEntity.getStreet());
            address.setCity(addressEntity.getCity());
            address.setZipCode(addressEntity.getZipcode());
            address.setState(addressEntity.getState());
            address.setCountry(addressEntity.getCountry());
        }

        return address;
    }

    /**
     * This method converts a address model object to a address entity object
     *
     * @param address     - the model object to be converted
     * @param hotelEntity - the associated hotel entity object
     * @return
     */
    public AddressEntity convertModelToEntity(Address address, HotelEntity hotelEntity) {

        AddressEntity addressEntity = null;

        if (Objects.nonNull(address)) {

            addressEntity = getAddressEntity(address, hotelEntity);

            String street = address.getStreet();
            if (Objects.nonNull(street)) {
                addressEntity.setStreet(street);
            }

            String city = address.getCity();
            if (Objects.nonNull(city)) {
                addressEntity.setCity(city);
            }

            Integer zipcode = address.getZipCode();
            if (Objects.nonNull(zipcode)) {
                addressEntity.setZipcode(zipcode);
            }

            String state = address.getState();
            if (Objects.nonNull(state)) {
                addressEntity.setState(state);
            }

            String country = address.getCountry();
            if (Objects.nonNull(country)) {
                addressEntity.setCountry(address.getCountry());

            }

        }
        return addressEntity;
    }

    /**
     * This method fetches the address entity
     *
     * @param address     - the model address object
     * @param hotelEntity - the hotelEntity Object to be saved in case there is no existing address record found
     * @return
     */
    private AddressEntity getAddressEntity(Address address, HotelEntity hotelEntity) {
        AddressEntity addressEntity = new AddressEntity();

        if (Objects.nonNull(address.getId()) && address.getId() != 0) {
            Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(address.getId());
            if (optionalAddressEntity.isPresent()) {
                addressEntity = optionalAddressEntity.get();
            }
        } else {
            addressEntity = new AddressEntity();
            addressEntity.setHotelEntity(hotelEntity);
        }
        return addressEntity;
    }

}
