package com.epam.guest.repository;

import com.epam.guest.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  The repository for GuestEntity
 */
@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Integer> {
}
