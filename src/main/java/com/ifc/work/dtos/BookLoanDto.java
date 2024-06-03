package com.ifc.work.dtos;

import com.ifc.work.persistence.UserCredentialsEntity;
import com.ifc.work.persistence.UserEntity;

import java.util.Date;

public record BookLoanDto(
        Long id,


        UserEntity user,

        UserCredentialsEntity userCredentialsId,

        Date loanDate,

        int loanDuration,

        Date returnLoanDate,
        boolean returned
) {

    @Override
    public Long id() {
        return id;
    }

    @Override
    public UserEntity user() {
        return user;
    }

    @Override
    public UserCredentialsEntity userCredentialsId() {
        return userCredentialsId;
    }

    @Override
    public Date loanDate() {
        return loanDate;
    }

    @Override
    public int loanDuration() {
        return loanDuration;
    }

    @Override
    public Date returnLoanDate() {
        return returnLoanDate;
    }

    @Override
    public boolean returned() {
        return returned;
    }
}
