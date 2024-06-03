package com.ifc.work.usecases.usecasesImpl;

import com.ifc.work.entities.User;
import com.ifc.work.gateways.UserGateway;
import com.ifc.work.usecases.GetAllUsersUseCase;



import java.util.List;

public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {

    private final UserGateway userGateway;

    public GetAllUsersUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<User> execute() {
        return userGateway.obtainAllUsers();
    }
}
