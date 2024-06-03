package com.ifc.work.dtos;

import lombok.Data;


public record AddressDto(
        Long id,
        String AdressIdentification,
        int Cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String number,
        String complement
) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String AdressIdentification() {
        return AdressIdentification;
    }

    @Override
    public int Cep() {
        return Cep;
    }

    @Override
    public String state() {
        return state;
    }

    @Override
    public String city() {
        return city;
    }

    @Override
    public String neighborhood() {
        return neighborhood;
    }

    @Override
    public String street() {
        return street;
    }

    @Override
    public String number() {
        return number;
    }

    @Override
    public String complement() {
        return complement;
    }
}
