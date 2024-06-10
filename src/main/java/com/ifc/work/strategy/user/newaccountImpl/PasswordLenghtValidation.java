package com.ifc.work.strategy.user.newaccountImpl;

import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.dtos.UserDto;
import com.ifc.work.dtos.UserPermissionsDto;
import com.ifc.work.strategy.user.NewAccountValidationStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordLenghtValidation implements NewAccountValidationStrategy {

    @SneakyThrows
    @Override
    public void execute(UserDto userDto, UserCredentialsDto userCredentialsDto, UserPermissionsDto userPermissionsDto) {
        if(userCredentialsDto.password().length() < 3 || userCredentialsDto.password().length() > 12) {
            throw new Exception("A senha deve ter no mínimo 3 caracteres e no máximo 12 caracteres.");
        }
    }




}
