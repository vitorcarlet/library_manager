package com.ifc.work.rest;

import com.ifc.work.dtos.AuthDto;
import com.ifc.work.dtos.RequestRefreshDto;
import com.ifc.work.dtos.TokenResponseDto;

import com.ifc.work.dtos.UserCredentialsDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RequestMapping("/auth")
public interface AuthControllerRest {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    TokenResponseDto auth(@RequestBody UserCredentialsDto userCredentialsDto);

    @PostMapping("/refresh-token")
     TokenResponseDto authRefreshToken(@RequestBody RequestRefreshDto requestRefreshDto);

}
