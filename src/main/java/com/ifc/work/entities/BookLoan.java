package com.ifc.work.entities;

import com.ifc.work.persistence.UserCredentialsEntity;
import com.ifc.work.persistence.UserEntity;

import java.util.Date;

public record BookLoan(

        Long id,


        UserEntity user,

        UserCredentialsEntity userCredentialsId,

        Date LoanDate,

        int LoanDuration,

        Date returnLoanDate,
        boolean returned
) {
}
