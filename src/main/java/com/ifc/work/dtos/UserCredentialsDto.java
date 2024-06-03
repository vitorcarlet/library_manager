package com.ifc.work.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;



public record UserCredentialsDto(
        String login,
        String password
) {
    @Override
    public String login() {
        return login;
    }

    @Override
    public String password() {
        return password;
    }
}
