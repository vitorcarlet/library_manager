package com.ifc.work.entities;

public record Adress(
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

}
