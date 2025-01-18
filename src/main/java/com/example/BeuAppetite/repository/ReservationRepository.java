package com.example.BeuAppetite.repository;

import com.example.BeuAppetite.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByDateTimeBetweenOrderByDateTimeDesc(LocalDateTime startDate, LocalDateTime endDate);

}