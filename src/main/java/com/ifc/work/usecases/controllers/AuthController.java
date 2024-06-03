package com.ifc.work.usecases.controllers;

import com.ifc.work.dtos.AuthDto;
import com.ifc.work.dtos.RequestRefreshDto;
import com.ifc.work.dtos.TokenResponseDto;
import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.rest.AuthControllerRest;
import com.ifc.work.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthController implements AuthControllerRest {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Override
    public TokenResponseDto auth(@RequestBody UserCredentialsDto userCredentialsDto) {

        var usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(userCredentialsDto.login(), userCredentialsDto.password());

        authenticationManager.authenticate(usuarioAutenticationToken);

        return authService.obterToken(userCredentialsDto);
    }

    @Override
    public TokenResponseDto authRefreshToken(@RequestBody RequestRefreshDto requestRefreshDto) {
        return authService.obterRefreshToken(requestRefreshDto.refreshToken());
    }
}
