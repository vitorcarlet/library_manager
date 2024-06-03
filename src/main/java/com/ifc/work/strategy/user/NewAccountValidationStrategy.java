package com.ifc.work.strategy.user;


import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.dtos.UserDto;
import com.ifc.work.dtos.UserPermissionsDto;

public interface NewAccountValidationStrategy {

    void execute(UserDto userDto, UserCredentialsDto userCredentialsDto, UserPermissionsDto userPermissionsDto);
}
