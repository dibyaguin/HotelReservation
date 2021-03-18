package com.epam.guest.repository;

import com.epam.guest.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  The repository for AddressEntity
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
