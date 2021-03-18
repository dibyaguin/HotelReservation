package com.epam.hotel.repository;

import com.epam.hotel.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository for RoomEntity
 */
@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    List<RoomEntity> findByIdNotIn(List<Integer> reservedRoomIds);
}

