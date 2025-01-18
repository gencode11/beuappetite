package com.example.BeuAppetite.configuration;

import com.example.BeuAppetite.entity.Reservation;
import com.example.BeuAppetite.repository.ClientRepository;
import com.example.BeuAppetite.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import  com.example.BeuAppetite.entity.*;
import java.time.LocalDateTime;

@Configuration
class DatabaseInitializer {

    // We will init the DB with some test data for app dev
    @Bean
    @Transactional
    CommandLineRunner initDatabase(ClientRepository clientRepository, ReservationRepository reservationRepository) {
        return args -> {
            // Create client
            Client client = new Client();
            client.setName("Vernon Nava");
            client.setEmail("Vernon.nava@rbc.com");
            client.setPhoneNumber("416-000-0000");
            client.setStatus("Enabled");
            client.setImportant(true);
            clientRepository.save(client);

            // Create reservations
            Reservation reservation1 = new Reservation();
            reservation1.setClient(client);
            reservation1.setDateTime(LocalDateTime.now());
            reservation1.setNotes("Celiac allergy guest Jurgz");
            reservation1.setNumGuests(5);
            reservationRepository.save(reservation1);

            Reservation reservation2 = new Reservation();
            reservation2.setClient(client);
            reservation2.setDateTime(LocalDateTime.now().minusHours(4));
            reservation2.setNotes("Anniversary");
            reservation2.setNumGuests(2);
            reservationRepository.save(reservation2);

            Reservation reservation3 = new Reservation();
            reservation3.setClient(client);
            reservation3.setDateTime(LocalDateTime.now().minusHours(6));
            reservation3.setNotes("Team Dinner!");
            reservation3.setNumGuests(10);
            reservationRepository.save(reservation3);
        };
    }
}