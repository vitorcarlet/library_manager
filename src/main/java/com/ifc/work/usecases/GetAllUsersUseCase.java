package com.ifc.work.usecases;



import com.ifc.work.entities.User;

import java.util.List;

public interface GetAllUsersUseCase {
    public List<User> execute();
}
