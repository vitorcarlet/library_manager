package com.ifc.work.entities;



public record UserCredentials(
        Long id,
        String login,
        String password,
        User userId
        ) {
}
