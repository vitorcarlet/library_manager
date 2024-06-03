package com.ifc.work.services;

import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.dtos.UserDto;

import com.ifc.work.dtos.UserPermissionsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {


    public ResponseEntity<String> signUp(UserDto userDto, UserCredentialsDto userCredentialsDto, UserPermissionsDto userPermissionsDto);
}
