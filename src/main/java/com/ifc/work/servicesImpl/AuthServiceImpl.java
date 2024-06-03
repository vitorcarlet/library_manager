package com.ifc.work.servicesImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ifc.work.dtos.AuthDto;
import com.ifc.work.dtos.TokenResponseDto;
import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.exceptions.UnauthorizedException;
import com.ifc.work.persistence.UserCredentialsEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.persistence.UserPermissionsEntity;
import com.ifc.work.repositories.UserCredentialsRepository;
import com.ifc.work.repositories.UserPermissionsRepository;
import com.ifc.work.repositories.UserRepository;
import com.ifc.work.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthServiceImpl implements AuthService {

//    @Value("${auth.jwt.token.secret}")
//    private String secretKey;
//
//    @Value("${auth.jwt.token.expiration}")
//    private Integer horaExpiracaoToken;
//
//    @Value("${auth.jwt.refresh-token.expiration}")
//    private Integer horaExpiracaoRefreshToken ;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private UserPermissionsRepository userPermissionsRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return  userPermissionsRepository.findByLogin(login);
    }

    @Override
    public TokenResponseDto obterToken(UserCredentialsDto userCredentialsDto) {
        UserCredentialsEntity userCredentials = userCredentialsRepository.findByLogin(userCredentialsDto.login());

        return TokenResponseDto
                .builder()
                .token(geraTokenJwt(userCredentials,3600))
                .refreshToken(geraTokenJwt(userCredentials,3600))
                .build();
    }

    public  String geraTokenJwt(UserCredentialsEntity userCredentials, Integer expiration) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("asd");
            
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(userCredentials.getLogin())
                    .withExpiresAt(geraDataExpiracao(expiration))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar o token! " +exception.getMessage());
        }
    }

    public String validaTokenJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("asd");

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    @Override
    public TokenResponseDto obterRefreshToken(String refreshToken) {

        String login = validaTokenJwt(refreshToken);

        UserCredentialsEntity userCredentials = userCredentialsRepository.findByLogin(login);
        UserEntity user = userRepository.getReferenceById(userCredentials.getUserId().getId());
        UserPermissionsEntity userPermissions = userPermissionsRepository.getReferenceById(user.getId());



        if (userCredentials == null) {
            throw new UnauthorizedException("UnauthorizedException");
        }

        var autentication = new UsernamePasswordAuthenticationToken(userCredentials, null, userPermissions.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(autentication);

        return TokenResponseDto
                .builder()
                .token(geraTokenJwt(userCredentials,3600))
                .refreshToken(geraTokenJwt(userCredentials,3600))
                .build();
    }

    private Instant geraDataExpiracao(Integer expiration) {
        return LocalDateTime.now()
                .plusHours(expiration)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
