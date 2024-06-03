package com.ifc.work.gateways;


import com.ifc.work.entities.User;
import com.ifc.work.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(user.name(), user.cpf(), user.birth(),user.gender(), user.registration(),user.activeUser());
    }

    public User toUser(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getCpf(), entity.getBirth(), entity.getGender(), entity.getRegistration(),entity.isActiveUser());
    }
}
