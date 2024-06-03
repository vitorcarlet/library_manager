package com.ifc.work.restImpl;

import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.dtos.UserDto;
import com.ifc.work.dtos.UserPermissionsDto;
import com.ifc.work.requests.user.SignUpRequest;
import com.ifc.work.rest.UserRest;
import com.ifc.work.services.UserService;
import com.ifc.work.utils.LibraryUtils;
import constants.LibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRestImpl implements UserRest {

    @Autowired
     UserService userService;


    @Override
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        try {
            // Extract DTOs from the request object
            UserDto userDto = request.getUserDto();
            UserCredentialsDto userCredentialsDto = request.getUserCredentialsDto();
            UserPermissionsDto userPermissionsDto = request.getUserPermissionsDto();

            // Call the userService to process the sign-up
            return userService.signUp(userDto, userCredentialsDto, userPermissionsDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String getAdmin() {
        return "yes";
    }

    @Override
    public String getUser() {
        return "yes";
    }

}
