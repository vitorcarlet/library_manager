package com.ifc.work.persistence;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "tb_adress")
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(nullable = false)
    String AdressIdentification;

    @Column(nullable = false)
    int cep;

    @Column(nullable = false)
    String state;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String neighborhood;

    @Column(nullable = false)
    String street;

    @Column(nullable = false)
    String number;

    @Column(nullable = false)
    String complement;

}
