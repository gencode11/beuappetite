package com.example.BeuAppetite.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// We could use LOMBOK here as well, but you'll need to configure your runtime env so we will use basic pojo for now

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    private LocalDateTime dateTime;
    private int numGuests;
    private String notes;

    public Reservation() {
    }

    public Reservation(Long id, Client client, LocalDateTime dateTime, int numGuests, String notes) {
        this.id = id;
        this.client = client;
        this.dateTime = dateTime;
        this.numGuests = numGuests;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}