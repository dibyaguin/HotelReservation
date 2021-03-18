package com.epam.hotel.mapper;

import com.epam.hotel.constant.ActiveType;
import com.epam.hotel.entity.HotelEntity;
import com.epam.hotel.entity.RoomEntity;
import com.epam.hotel.model.Room;
import com.epam.hotel.repository.RoomRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    @Autowired
    private RoomRepository roomRepository;

    /**
     * This method converts list of RoomEntity objects to list of Room Model objects
     *
     * @param roomEntities - the list of RoomEntity Objects to be converted
     * @return
     */
    public List<Room> convertEntityToModel(List<RoomEntity> roomEntities) {
        return CollectionUtils.emptyIfNull(roomEntities)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    /**
     * This method converts a RoomEntity Object to a model Room Object
     *
     * @param roomEntity - the RoomEntity object to be converted
     * @return
     */
    private Room convertEntityToModel(RoomEntity roomEntity) {
        Room room = new Room();
        room.setId(roomEntity.getId());
        room.setHotelId(roomEntity.getHotelEntity().getId());
        room.setRoomNo(roomEntity.getRoomNo());
        room.setRoomType(roomEntity.getRoomType());
        room.setPrice(roomEntity.getPrice());
        room.setRoomDescription(roomEntity.getRoomDescription());
        room.setActive(ActiveType.YES.getValue().equalsIgnoreCase(roomEntity.getIsActive()) ? Boolean.TRUE : Boolean.FALSE);

        return room;
    }

    /**
     * This method converts list of Room Model objects to list of Room Entity objects
     *
     * @param rooms       - the list of model objects to be converted
     * @param hotelEntity - the associated hotel entity object
     * @return
     */
    public List<RoomEntity> convertModelToEntity(List<Room> rooms, HotelEntity hotelEntity) {
        return CollectionUtils.emptyIfNull(rooms)
                .stream()
                .filter(Objects::nonNull)
                .map(room -> convertModelToEntity(room, hotelEntity))
                .collect(Collectors.toList());
    }

    /**
     * This method converts a Model Room Object to a Room Entity object
     *
     * @param room        - the Room model object to be converted
     * @param hotelEntity - the associated hotel entity object
     * @return
     */
    private RoomEntity convertModelToEntity(Room room, HotelEntity hotelEntity) {

        RoomEntity roomEntity = new RoomEntity();

        if (Objects.nonNull(room.getId()) && room.getId() != 0) {
            Optional<RoomEntity> optionalRoomEntity = roomRepository.findById(room.getId());
            if (optionalRoomEntity.isPresent()) {
                roomEntity = optionalRoomEntity.get();
            }
        } else {
            roomEntity = new RoomEntity();
            roomEntity.setHotelEntity(hotelEntity);
        }

        Integer roomNo = room.getRoomNo();
        if (Objects.nonNull(roomNo)) {
            roomEntity.setRoomNo(roomNo);
        }

        String roomType = room.getRoomType();
        if (Objects.nonNull(roomType)) {
            roomEntity.setRoomType(roomType);
        }

        String roomDescription = room.getRoomDescription();
        if (Objects.nonNull(roomDescription)) {
            roomEntity.setRoomDescription(roomDescription);
        }

        Double roomPrice = room.getPrice();
        if (Objects.nonNull(roomPrice)) {
            roomEntity.setPrice(roomPrice);
        }

        Boolean isActive = room.isActive();
        if (Objects.nonNull(isActive)) {
            roomEntity.setIsActive(isActive.booleanValue() ? ActiveType.YES.getValue() : ActiveType.NO.getValue());
        }

        return roomEntity;
    }
}
