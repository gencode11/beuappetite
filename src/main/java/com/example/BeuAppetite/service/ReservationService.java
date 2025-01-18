package com.example.BeuAppetite.service;

import com.example.BeuAppetite.entity.Reservation;
import com.example.BeuAppetite.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public
class ReservationService {
    @Value("${beuappetite.max.capacity}")
    private int MAX_CAPACITY;
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getTodaysReservationsDesc() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        return reservationRepository.findByDateTimeBetweenOrderByDateTimeDesc(startOfDay, endOfDay);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) throws Exception {
        validateRequest(reservation);
        return reservationRepository.save(reservation);
    }

    private void validateRequest(Reservation reservation) throws Exception {
        // Do any checks here
        // We will also check the size of the reservations to determine if we will create another

        // check reservations for that day
        LocalDateTime startOfDay = reservation.getDateTime().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = reservation.getDateTime().toLocalDate().atTime(LocalTime.MAX);

        if (reservationRepository.findByDateTimeBetweenOrderByDateTimeDesc(startOfDay, endOfDay).size() >= MAX_CAPACITY)
            throw new Exception("Capacity reached");
    }
}