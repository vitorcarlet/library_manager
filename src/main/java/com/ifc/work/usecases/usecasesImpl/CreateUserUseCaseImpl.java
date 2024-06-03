package com.ifc.work.usecases.usecasesImpl;

import com.ifc.work.entities.User;
import com.ifc.work.exceptions.BusinessException;
import com.ifc.work.gateways.UserGateway;
import com.ifc.work.usecases.CreateUserUseCase;


public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }


    @Override
    public User execute(User user) {
        User userExist = userGateway.findByCpf(user.cpf());
        if(userExist != null) {
            throw new BusinessException("Já existe uma pessoa com CPF "+user.cpf() + " cadastrado!");
        }
        return userGateway.createUser(user);
    }
}
