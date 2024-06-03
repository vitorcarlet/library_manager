package com.ifc.work.services;

import com.ifc.work.dtos.AuthDto;
import com.ifc.work.dtos.TokenResponseDto;
import com.ifc.work.dtos.UserCredentialsDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    public TokenResponseDto obterToken(UserCredentialsDto userCredentialsDto);

    public String validaTokenJwt(String token);

    TokenResponseDto obterRefreshToken(String s);
}
