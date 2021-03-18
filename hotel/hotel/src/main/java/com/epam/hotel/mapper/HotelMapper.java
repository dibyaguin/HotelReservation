package com.epam.hotel.mapper;

import com.epam.hotel.entity.HotelEntity;
import com.epam.hotel.model.Address;
import com.epam.hotel.model.Hotel;
import com.epam.hotel.model.Room;
import com.epam.hotel.repository.HotelRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This the Mapper class for mapping between HotelEntity and Hotel Model
 */
@Component
public class HotelMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private HotelRepository hotelRepository;

    /**
     * This method converts list of HotelEntity objects to list of Hotel model objects
     *
     * @param hotelEntities -  the HotelEntity object to be converted
     * @return
     */
    public List<Hotel> convertEntityToModel(List<HotelEntity> hotelEntities) {

        return CollectionUtils.emptyIfNull(hotelEntities)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    /**
     * This method converts a HotelEntity object to a Model Hotel Object
     *
     * @param hotelEntity - the HotelEntity Object to be converted
     * @return
     */
    public Hotel convertEntityToModel(HotelEntity hotelEntity) {

        Hotel hotel = new Hotel();
        hotel.setId(hotelEntity.getId());
        hotel.setName(hotelEntity.getName());
        hotel.setPhone(hotelEntity.getPhone());
        hotel.setEmail(hotelEntity.getEmail());

        Address address = addressMapper.convertEntityToModel(hotelEntity.getAddressEntity());
        if (Objects.nonNull(address)) {
            hotel.setAddress(address);
        }

        List<Room> rooms = roomMapper.convertEntityToModel(hotelEntity.getRoomEntities());
        if (CollectionUtils.isNotEmpty(rooms)) {
            hotel.setRooms(rooms);
        }

        return hotel;
    }

    /**
     * This method converts Hotel Model Object to HotelEntity Object
     *
     * @param hotel       - the model object to be converted
     * @param hotelEntity - the entity object to be saved in the db
     * @return
     */
    public HotelEntity convertModelToEntity(Hotel hotel, HotelEntity hotelEntity) {

        if (Objects.nonNull(hotel)) {

            String hotelName = hotel.getName();
            if (Objects.nonNull(hotelName)) {
                hotelEntity.setName(hotelName);
            }

            String email = hotel.getEmail();
            if (Objects.nonNull(email)) {
                hotelEntity.setEmail(email);
            }

            Long phone = hotel.getPhone();
            if (Objects.nonNull(phone)) {
                hotelEntity.setPhone(phone);
            }

            HotelEntity savedHotelEntity = hotelRepository.save(hotelEntity);

            hotelEntity.setAddressEntity(
                    addressMapper.convertModelToEntity(hotel.getAddress(), savedHotelEntity));

            hotelEntity.setRoomEntities(roomMapper.convertModelToEntity(hotel.getRooms(), savedHotelEntity));
        }
        return hotelEntity;

    }
}
