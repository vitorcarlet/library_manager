package com.ifc.work.persistence;


import com.ifc.work.observer.user.UserObserver;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_users")
public class UserEntity implements UserObserver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    String name;

    @Column(name= "cpf",nullable = false)
    String cpf;

    @Column(name="birth",nullable = false)
    Date birth;

    @Column(name="gender",nullable = false)
    String gender;

    @Column(name="registration",nullable = false)
    int registration;

    @Column(name="activeUser",nullable = false)
    boolean activeUser;

    public UserEntity() {

    }

    public UserEntity(String name, String cpf, Date birth, String gender,  int registration, boolean activeUser) {
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
        this.gender = gender;
        this.registration = registration;
        this.activeUser = activeUser;
    }

    public UserEntity(Long id, boolean activeUser, Date birth, String cpf, String gender, int registration,  String name  ) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
        this.gender = gender;
        this.registration = registration;
        this.activeUser = activeUser;
    }

    public UserEntity(Long id, boolean activeUser, Date birth, String cpf, String gender,  String name  ) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
        this.gender = gender;
        this.registration = registration;
        this.activeUser = activeUser;
    }

    public UserEntity(String name, String cpf, Date birth, String gender, boolean activeUser) {
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
        this.gender = gender;
        this.activeUser = activeUser;
    }

    @Override
    public void update(BookEntity book) {

    }

    @Override
    public void notifyReturn(BookEntity book, Date dataDevolucao) {

    }

    @Override
    public void updateReturn(BookEntity book, Date returnDate) {

    }

}
