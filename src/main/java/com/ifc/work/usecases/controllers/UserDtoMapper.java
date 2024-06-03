package com.ifc.work.usecases.controllers;


import com.ifc.work.dtos.UserDto;
import com.ifc.work.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserDto toDto(User user){
        return new UserDto(user.name(), user.cpf(), user.birth(),user.gender(),user.registration(), user.activeUser());
    }

    public User toDomain(UserDto userDto){
        return new User(null, userDto.name(), userDto.cpf(), userDto.birth(), userDto.gender(), userDto.registration(), userDto.activeUser());
    }

}
