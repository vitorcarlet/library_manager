package com.ifc.work.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "BookStoreVisits")
public class BookStoreVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private java.time.LocalDate visitDate;
    private Integer numberOfPeople;

    // Construtores
    public BookStoreVisitEntity() {
    }

    public BookStoreVisitEntity(java.time.LocalDate visitDate, Integer numberOfPeople) {
        this.visitDate = visitDate;
        this.numberOfPeople = numberOfPeople;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.time.LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(java.time.LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}