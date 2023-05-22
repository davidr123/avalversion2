package com.example.pruebasaval.pruebaversion2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime airDate;
    private boolean consultedFromDatabase;
    private int consultationCount;

    // Constructor, getters y setters

    // Métodos adicionales
    public void incrementConsultationCount() {
        this.consultationCount++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getAirDate() {
        return airDate;
    }

    public void setAirDate(LocalDateTime airDate) {
        this.airDate = airDate;
    }

    public boolean isConsultedFromDatabase() {
        return consultedFromDatabase;
    }

    public void setConsultedFromDatabase(boolean consultedFromDatabase) {
        this.consultedFromDatabase = consultedFromDatabase;
    }

    public int getConsultationCount() {
        return consultationCount;
    }

    public void setConsultationCount(int consultationCount) {
        this.consultationCount = consultationCount;
    }

    

}
