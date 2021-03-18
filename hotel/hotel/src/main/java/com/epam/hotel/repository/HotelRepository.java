package com.epam.hotel.repository;

import com.epam.hotel.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository for HotelEntity
 */
@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {

}
