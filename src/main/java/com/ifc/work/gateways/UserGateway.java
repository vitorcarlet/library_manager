package com.ifc.work.gateways;

import com.ifc.work.entities.User;


import java.util.List;

public interface UserGateway {

    User createUser(User user);

     User findByCpf(String cpf);

    List<User> obtainAllUsers();
}
