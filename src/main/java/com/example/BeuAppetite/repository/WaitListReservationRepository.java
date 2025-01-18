package com.example.BeuAppetite.repository;

import com.example.BeuAppetite.entity.WaitListReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitListReservationRepository extends JpaRepository<WaitListReservation, Long> {}