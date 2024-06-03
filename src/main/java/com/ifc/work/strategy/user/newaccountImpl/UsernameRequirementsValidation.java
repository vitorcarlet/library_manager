package com.ifc.work.strategy.user.newaccountImpl;

import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.dtos.UserDto;
import com.ifc.work.dtos.UserPermissionsDto;
import com.ifc.work.strategy.user.NewAccountValidationStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class UsernameRequirementsValidation implements NewAccountValidationStrategy {

    @SneakyThrows
    @Override
    public void execute(UserDto userDto, UserCredentialsDto userCredentialsDto, UserPermissionsDto userPermissionsDto) {
        System.out.println("Validando requerimentos do username");
        if(!userDto.name().matches("^[a-zA-Z]+$")){
            throw new Exception("O nome deve conter apenas letras.");
        }
    }



}