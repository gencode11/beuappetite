package com.example.BeuAppetite.resource;

import com.example.BeuAppetite.entity.Reservation;
import com.example.BeuAppetite.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationResource {
    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // This will be the main method we would use to read daily reservations
    @GetMapping("/today")
    public ResponseEntity<List<Reservation>> getTodaysReservations() {
        return ResponseEntity.ok(reservationService.getTodaysReservationsDesc());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Reservations can be created here we would use the secondary table of waitlist resv but for simplicity will use the resv table itself.
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) throws Exception {
        Reservation savedReservation = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
    }
}