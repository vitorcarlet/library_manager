package com.ifc.work.servicesImpl;

import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.dtos.UserDto;
import com.ifc.work.dtos.UserPermissionsDto;
import com.ifc.work.persistence.UserCredentialsEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.persistence.UserPermissionsEntity;
import com.ifc.work.repositories.UserCredentialsRepository;
import com.ifc.work.repositories.UserPermissionsRepository;
import com.ifc.work.repositories.UserRepository;
import com.ifc.work.services.UserService;
import com.ifc.work.strategy.user.NewAccountValidationStrategy;
import com.ifc.work.utils.LibraryUtils;
import constants.LibraryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPermissionsRepository userPermissionsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final List<NewAccountValidationStrategy> newAccountValidationStrategy;

    public UserServiceImpl(List<NewAccountValidationStrategy> newAccountValidationStrategy) {
        this.newAccountValidationStrategy = newAccountValidationStrategy;
    }




    @Override
    public ResponseEntity<String> signUp(UserDto userDto, UserCredentialsDto userCredentialsDto, UserPermissionsDto userPermissionsDto) {
        //newAccountValidationStrategy.forEach(validation -> validation.execute(userDto,userCredentialsDto,userPermissionsDto));

        if (Objects.isNull(userPermissionsDto)) {
            // Handle null values appropriately, e.g., set defaults or reject the request
            return ResponseEntity.badRequest().body("UserPermissionsDto must not contain null values");
        }


        log.info("chegou no signup");
        log.info(userPermissionsDto.toString());
        log.info(userCredentialsDto.toString());
        log.info(userDto.toString());
        try{

            var passwordHash = passwordEncoder.encode(userCredentialsDto.password());

            UserEntity user = new UserEntity(userDto.name(),userDto.cpf(), userDto.birth() ,userDto.gender(),true);
             UserCredentialsEntity userCredentials = new UserCredentialsEntity(userCredentialsDto.login(),passwordHash,user);
            UserPermissionsEntity userPermissions = new UserPermissionsEntity(userPermissionsDto.isAdmin(), userPermissionsDto.isOperator(),userPermissionsDto.isAssistant(), userCredentials);
            userRepository.save(user);
            userCredentialsRepository.save(userCredentials);
            userPermissionsRepository.save(userPermissions);

            return LibraryUtils.getResponseEntity("Succesfully Registered",HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }




}
