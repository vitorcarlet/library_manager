package com.ifc.work.config;


import com.ifc.work.exceptions.UnauthorizedException;
import com.ifc.work.persistence.UserCredentialsEntity;
import com.ifc.work.persistence.UserPermissionsEntity;
import com.ifc.work.repositories.UserCredentialsRepository;
import com.ifc.work.repositories.UserPermissionsRepository;
import com.ifc.work.repositories.UserRepository;
import com.ifc.work.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final UserRepository userRepository;

    private final UserPermissionsRepository userPermissionsRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    @Autowired
    public SecurityFilter(AuthService authService, UserRepository userRepository, UserPermissionsRepository userPermissionsRepository, UserCredentialsRepository userCredentialsRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.userPermissionsRepository = userPermissionsRepository;
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extraiTokeHeader(request);

        if (token != null) {
            log.info(token);
            String login = authService.validaTokenJwt(token);
            UserCredentialsEntity userCredentials = userCredentialsRepository.findByLogin(login);
            if (userCredentials == null) {
                throw  new UnauthorizedException("Unauthorized");
            }
//            UserEntity userEntity = userCredentials.getUserId();
//            if (userCredentials == null) {
//                throw  new UnauthorizedException("Unauthorized");
//            }
            log.info(String.valueOf(userCredentials.getId()));
            UserPermissionsEntity userPermissions = userPermissionsRepository.findByUserCredentialsId(userCredentials.getId());
            if (userPermissions == null) {
                throw  new UnauthorizedException("Unauthorized");
            }


            var autentication = new UsernamePasswordAuthenticationToken(userCredentials, null, userPermissions.getAuthorities());
            log.info(String.valueOf(autentication));

            SecurityContextHolder.getContext().setAuthentication(autentication);
        }

        filterChain.doFilter(request, response);
    }

    public String extraiTokeHeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }

        if (!authHeader.split(" ")[0].equals("Bearer")) {
            return  null;
        }

        return authHeader.split(" ")[1];
    }
}
