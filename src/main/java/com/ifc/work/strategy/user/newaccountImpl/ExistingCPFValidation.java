package com.ifc.work.strategy.user.newaccountImpl;

import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.dtos.UserDto;
import com.ifc.work.dtos.UserPermissionsDto;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.repositories.UserRepository;

import com.ifc.work.strategy.user.NewAccountValidationStrategy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ExistingCPFValidation  implements NewAccountValidationStrategy {

    @Autowired
    UserRepository userRepository;

    @SneakyThrows
    @Override
    public void execute(UserDto userDto, UserCredentialsDto userCredentialsDto, UserPermissionsDto userPermissionsDto) {
        UserEntity user = userRepository.findByCpf(userDto.cpf());
        if(!Objects.isNull(user)){
            throw new Exception("Ja existe um usuario registrado com esse CPF");
        }
    }
}
