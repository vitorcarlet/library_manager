package com.ifc.work.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifc.work.persistence.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWrapper {
    private String name;
    private String cpf;
    private Date birth;
    private String gender;
    private int registration;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean activeUser;

    public UserWrapper(String name, String cpf, Date birth, String gender, int registration, boolean activeUser) {
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
        this.gender = gender;
        this.registration = registration;
        this.activeUser = activeUser;
    }

    public UserWrapper(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.cpf =  userEntity.getCpf();
        this.birth =  userEntity.getBirth();
        this.gender =  userEntity.getGender();
        this.registration =  userEntity.getRegistration();
        this.activeUser =  userEntity.isActiveUser();
    }
}
